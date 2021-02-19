package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.GodData;
import com.astro.SmiteSolver.entity.GodDataHighMMR;
import com.astro.SmiteSolver.entity.GodDataLowMMR;
import com.astro.SmiteSolver.entity.GodNames;
import com.astro.SmiteSolver.repository.GodNamesRepository;
import com.astro.smitebasic.api.SmiteAPI;
import com.astro.smitebasic.api.Utils;
import com.astro.smitebasic.objects.characters.GodInfo;
import com.astro.smitebasic.objects.gamedata.matches.MatchInfo;
import com.astro.smitebasic.objects.gamedata.matches.MultiMatchInfo;
import com.astro.smitebasic.objects.player.matches.PlayerMatchData;
import com.astro.smitebasic.utils.Language;
import com.astro.smitebasic.utils.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class MatchParserService {

    @Value("${smite.api}")
    private String apiUri;

    @Value("${smite.dev-id}")
    private String devID;

    @Value("${smite.auth-key}")
    private String authKey;

    private final float HIGH_MMR_BOUNDARY = 1680.0f;

    @Autowired
    private SmiteAPI api;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private PerformanceDataService performanceDataService;

    @Autowired
    private GodNamesRepository godNamesRepository;

    @PostConstruct
    private void initializeAPI() {
        api.setCredentials(apiUri, devID, authKey);
    }

    public void updateData() {
        checkForPatchUpdates();
        updateService.getUpdatableDates().forEach(date -> {
            Map<Integer, GodDataHighMMR> godDataHighMMRMap = new HashMap<>();
            Map<Integer, GodDataLowMMR> godDataLowMMRMap = new HashMap<>();
            int matchCount = 0;

            for(int parseHours = 0; parseHours < 24; parseHours++) {
                for (Map.Entry<Integer, PlayerMatchData[]> matchDataEntry : getDailyMultiMatchData(date, parseHours).entrySet()) {
                    PlayerMatchData[] matchInfo = matchDataEntry.getValue();

                    List<Float> averageMMRList = new ArrayList<>();

                    // Parses through each player's match data where it lists their stats and god played,
                    // their stats are then copied into a custom map for either high mmr or low mmr,
                    // after the stats are built up for every copied god, the data will be inputted into the GodDataRepository.
                    for (PlayerMatchData playerMatchData : matchInfo) {
                        Integer key = playerMatchData.getGodID();
                        Optional<GodNames> name = godNamesRepository.findById(key);
                        GodData data;

                        if (name.isPresent()) {

                            if (playerMatchData.getRankStatConquest() < HIGH_MMR_BOUNDARY) {
                                data = godDataHighMMRMap.containsKey(key) ? godDataHighMMRMap.get(key) :
                                        new GodDataHighMMR(date, key, name.get().getGodName());
                                godDataHighMMRMap.put(key, (GodDataHighMMR) configureGodData(playerMatchData, data));

                            } else {
                                data = godDataLowMMRMap.containsKey(key) ? godDataLowMMRMap.get(key) :
                                        new GodDataLowMMR(date, key, name.get().getGodName());
                                godDataLowMMRMap.put(key, (GodDataLowMMR) configureGodData(playerMatchData, data));

                            }
                        }
                        averageMMRList.add(playerMatchData.getRankStatConquest());
                    }

                    // Builds up ban data for playable gods
                    for (Integer bannedGodID : getBannedGodIDs(matchInfo[0])) {
                        GodData data;
                        Optional<GodNames> name = godNamesRepository.findById(bannedGodID);

                        if (name.isPresent()) {

                            if (getMMRAverage(averageMMRList) < HIGH_MMR_BOUNDARY) {
                                data = godDataHighMMRMap.containsKey(bannedGodID) ? godDataHighMMRMap.get(bannedGodID) :
                                        new GodDataHighMMR(date, bannedGodID, name.get().getGodName());
                                godDataHighMMRMap.put(bannedGodID, (GodDataHighMMR) incrementBans(data));

                            } else {
                                data = godDataLowMMRMap.containsKey(bannedGodID) ? godDataLowMMRMap.get(bannedGodID) :
                                        new GodDataLowMMR(date, bannedGodID, name.get().getGodName());
                                godDataHighMMRMap.put(bannedGodID, (GodDataHighMMR) incrementBans(data));

                            }
                        }
                    }
                    matchCount++;
                }
            }
            performanceDataService.configureHighMMRGodData(godDataHighMMRMap);
            performanceDataService.configureLowMMRGodData(godDataLowMMRMap);
            performanceDataService.configureMatchData(date, matchCount);
        });
    }

    public Map<Integer, PlayerMatchData[]> getDailyMultiMatchData(LocalDate date, int hour) {
        Integer[] matchIDs = Arrays.stream(api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), date, hour))
                .map(MatchInfo::getMatchID)
                .toArray(Integer[]::new);
        return api.getMultipleMatchData(matchIDs).getPlayerMatchDataList();
    }

    public void checkForPatchUpdates() {
        if (!updateService.hasBeenUpdatedToday()) {
            if (updateService.isUpdatableDate(Double.parseDouble(api.getPatchInfo()[0].getVersion_string()))) {

                for (GodInfo info : api.getGods(Language.ENGLISH.getLanguageID())) {

                    Integer godID = info.getGodID();
                    if (checkNewGod(godID)) {
                        godNamesRepository.save(new GodNames(godID, info.getName()));
                    }
                }
            }
        }
    }

    public boolean checkNewGod(Integer godID) {
        return godNamesRepository.findById(godID).isEmpty();
    }

    public GodData configureGodData(PlayerMatchData playerMatchData, GodData data) {
        if (getWinStatus(playerMatchData.getSideSelection(), playerMatchData.getWinningSide()) == 1) {
            incrementWins(data);
        }
        incrementMatchesPlayed(data);

        addSkins(data, playerMatchData.getSkin());
        addItems(data, getPlayerItems(playerMatchData));
        addActives(data, getPlayerActives(playerMatchData));
        addDamageStats(data, playerMatchData.getDamagePlayer(), playerMatchData.getBasicAttackDamage(), playerMatchData.getDamageMitigated());
        return data;
    }

    /**
     * @param data is a data piece created by parsing through matches obtained from the Smite API
     */
    public GodData incrementBans(GodData data) {
        data.setBans(data.getBans() + 1);
        return data;
    }

    public void incrementMatchesPlayed(GodData data) {
        data.setMatchesPlayed(data.getMatchesPlayed() + 1);
    }

    public void incrementWins(GodData data) {
        data.setWins(data.getWins() + 1);
    }

    public void addSkins(GodData data, String skin) {
        Map<String, Integer> skins = data.getSkinsUsed();
        if (skins.containsKey(skin)) {
            skins.put(skin, skins.get(skin) + 1);
        } else {
            skins.put(skin, 1);
        }
    }

    public void addItems(GodData data, List<String> playerItems) {
        Map<String, Integer> items = data.getPopularItems();
        for (String item : playerItems) {
            if (items.containsKey(item)) {
                items.put(item, items.get(item) + 1);
            } else {
                items.put(item, 1);
            }
        }
    }

    public void addActives(GodData data, List<String> playerActives) {
        Map<String, Integer> actives = data.getPopularActives();
        for (String active : playerActives) {
            if (actives.containsKey(active)) {
                actives.put(active, actives.get(active) + 1);
            } else {
                actives.put(active, 1);
            }
        }
    }

    public void addDamageStats(GodData data, Integer damageDone, Integer basicAttackDamageDone, Integer damageMitigated) {
        List<Integer> damageScores = data.getDamageDone();
        damageScores.add(damageDone);
        data.setDamageDone(damageScores);

        List<Integer> basicAttackScores = data.getBasicAttackDamage();
        basicAttackScores.add(basicAttackDamageDone);
        data.setBasicAttackDamage(basicAttackScores);

        List<Integer> damageMitigatedScores = data.getDamageMitigated();
        damageMitigatedScores.add(damageMitigated);
        data.setDamageMitigated(damageMitigatedScores);
    }

    public Integer makeDataID(LocalDate date, Integer godID) {
        return date.getDayOfMonth() + date.getMonthValue() + date.getYear() + godID;
    }

    private List<String> getPlayerItems(PlayerMatchData data) {
        return Arrays.asList(data.getItemPurch1(), data.getItemPurch2(), data.getItemPurch3(),
                data.getItemPurch4(), data.getItemPurch5(), data.getItemPurch6());
    }

    private List<String> getPlayerActives(PlayerMatchData data) {
        return Arrays.asList(data.getItemActive1(), data.getItemActive2(), data.getItemActive3(), data.getItemActive4());
    }

    private List<Integer> getBannedGodIDs(PlayerMatchData data) {
        return Arrays.asList(data.getBan1ID(), data.getBan2ID(), data.getBan3ID(), data.getBan4ID(), data.getBan5ID(),
                data.getBan6ID(), data.getBan7ID(), data.getBan8ID(), data.getBan9ID(), data.getBan10ID());
    }

    // Win represents 1, a loss represents 0
    private Integer getWinStatus(Integer taskForce, Integer winningTaskForce) {
        return taskForce.equals(winningTaskForce) ? 1 : 0;
    }

    private Float getMMRAverage(List<Float> averageMMR) {
        return averageMMR.stream().reduce(0.0F, Float::sum) / averageMMR.size();
    }

    public void updateVersion() {
        String versionString = Utils.parseSingleEntry(api.getPatchInfo()).getVersion_string();
    }

    private boolean isUpdatableDate(LocalDate prevDate) {
        LocalDate currentDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"));
        return currentDate.minusDays(1).isAfter(prevDate);
    }

}
