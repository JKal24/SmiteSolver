package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.DailyGodData;
import com.astro.SmiteSolver.entity.DailyGodDataHighMMR;
import com.astro.SmiteSolver.entity.DailyGodDataLowMMR;
import com.astro.SmiteSolver.entity.GodName;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.smitebasic.api.SmiteAPI;
import com.astro.smitebasic.api.Utils;
import com.astro.smitebasic.objects.characters.GodInfo;
import com.astro.smitebasic.objects.gamedata.matches.MatchInfo;
import com.astro.smitebasic.objects.player.matches.PlayerMatchData;
import com.astro.smitebasic.utils.Language;
import com.astro.smitebasic.utils.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Service
public class MatchParserService {

    @Value("${smite.api}")
    private String apiUri;

    @Value("${smite.dev-id}")
    private String devID;

    @Value("${smite.auth-key}")
    private String authKey;

    @Autowired
    private SmiteAPI api;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private PerformanceDataService performanceDataService;

    @Autowired
    private GodNameRepository godNameRepository;

    @PostConstruct
    private void initializeAPI() {
        api.setCredentials(apiUri, devID, authKey);
    }

    public void updateData() {
        if (updateService.hasBeenUpdatedToday()) {
            return;
        }
        // Updates god list if version change
        updatePatch();

        LocalDate updateDate = utils.getComparableDate(1);

        Map<Integer, DailyGodDataHighMMR> godDataHighMMRMap = new HashMap<>();
        Map<Integer, DailyGodDataLowMMR> godDataLowMMRMap = new HashMap<>();
        int matchCountHighMMR = 0;
        int matchCountLowMMR = 0;

        for(int parseHours = 0; parseHours < 24; parseHours++) {
            List<PlayerMatchData> matchInfo = getDailyMultiMatchData(updateDate, parseHours);
            List<Float> averageMMRList = new ArrayList<>();
            PlayerMatchData currentPlayerData = matchInfo.get(0);
            int currentMatchID = currentPlayerData.getMatch();

            // Parses through each player's match data where it lists their stats and god played,
            // their stats are then copied into a custom map for either high mmr or low mmr,
            // after the stats are built up for every copied god, the data will be inputted into the DailyGodDataRepository.
            for (PlayerMatchData playerMatchData : matchInfo) {
                Integer key = playerMatchData.getGodID();
                Optional<GodName> name = godNameRepository.findById(key);
                DailyGodData data;

                if (name.isPresent()) {

                    if (playerMatchData.getRankStatConquest() < utils.HIGH_MMR_BOUNDARY) {
                        data = godDataHighMMRMap.containsKey(key) ? godDataHighMMRMap.get(key) :
                                new DailyGodDataHighMMR(updateDate, key, name.get().getGodName());
                        godDataHighMMRMap.put(key, (DailyGodDataHighMMR) configureGodData(playerMatchData, data));

                    } else {
                        data = godDataLowMMRMap.containsKey(key) ? godDataLowMMRMap.get(key) :
                                new DailyGodDataLowMMR(updateDate, key, name.get().getGodName());
                        godDataLowMMRMap.put(key, (DailyGodDataLowMMR) configureGodData(playerMatchData, data));

                    }
                }
                averageMMRList.add(playerMatchData.getRankStatConquest());

                // For every unique match, create ban data for gods
                if (!playerMatchData.getMatch().equals(currentMatchID)) {
                    boolean highMMR = getMMRAverage(averageMMRList) > utils.HIGH_MMR_BOUNDARY;
                    // Builds up ban data for playable gods
                    for (Integer bannedGodID : getBannedGodIDs(currentPlayerData)) {
                        DailyGodData bannedGodData;
                        Optional<GodName> bannedGodName = godNameRepository.findById(bannedGodID);

                        if (bannedGodName.isPresent()) {

                            if (highMMR) {
                                bannedGodData = godDataHighMMRMap.containsKey(bannedGodID) ? godDataHighMMRMap.get(bannedGodID) :
                                        new DailyGodDataHighMMR(updateDate, bannedGodID, bannedGodName.get().getGodName());
                                godDataHighMMRMap.put(bannedGodID, (DailyGodDataHighMMR) incrementBans(bannedGodData));

                            } else {
                                bannedGodData = godDataLowMMRMap.containsKey(bannedGodID) ? godDataLowMMRMap.get(bannedGodID) :
                                        new DailyGodDataLowMMR(updateDate, bannedGodID, bannedGodName.get().getGodName());
                                godDataHighMMRMap.put(bannedGodID, (DailyGodDataHighMMR) incrementBans(bannedGodData));

                            }
                        }
                    }
                    if (highMMR) {
                        matchCountHighMMR++;
                    } else {
                        matchCountLowMMR++;
                    }
                    averageMMRList = new ArrayList<>();
                }
            }


        }
        performanceDataService.compileGodData(godDataHighMMRMap, godDataLowMMRMap, matchCountHighMMR, matchCountLowMMR);
        performanceDataService.configureMatchData(updateDate, matchCountHighMMR, matchCountLowMMR);
    }

    public List<PlayerMatchData> getDailyMultiMatchData(LocalDate date, int hour) {
        MatchInfo[] info = api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), date, hour);
        Integer[] matchIDs = Arrays.stream(info)
                .map(MatchInfo::getMatchID)
                .toArray(Integer[]::new);
        List<PlayerMatchData> data = api.getMultipleMatchData(matchIDs);
        return data;
    }

    public List<PlayerMatchData> getDailyMultiMatchData(LocalDate date, int hour, int minutes) {
        Integer[] matchIDs = Arrays.stream(api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), date, hour, minutes))
                .map(MatchInfo::getMatchID)
                .toArray(Integer[]::new);
        return api.getMultipleMatchData(matchIDs);
    }

    public void updatePatch() {
        Double version = Utils.parseSingleEntry(api.getPatchInfo()).getVersion();

        if (updateService.isUpdatableVersion(version)) {

            GodInfo[] godList = api.getGods(Language.ENGLISH.getLanguageID());
            for (GodInfo info : godList) {

                Integer godID = info.getGodID();
                if (checkNewGod(godID)) {
                    godNameRepository.save(new GodName(godID, info.getName()));
                }
            }
        }

        updateService.addUpdate(utils.getComparableDate(1), version);
        updateService.cleanUpdates();
    }

    public boolean checkNewGod(Integer godID) {
        return godNameRepository.findById(godID).isEmpty();
    }

    public DailyGodData configureGodData(PlayerMatchData playerMatchData, DailyGodData data) {
        int matchesPlayed = data.getMatchesPlayed();

        if (getWinStatus(playerMatchData.getSideSelection(), playerMatchData.getWinningSide()) == 1) {
            incrementWins(data);
        }
        addSkins(data, playerMatchData.getSkin());
        addItems(data, getPlayerItems(playerMatchData));
        addActives(data, getPlayerActives(playerMatchData));
        addMatchStats(data, matchesPlayed, playerMatchData.getDamagePlayer(),
                playerMatchData.getBasicAttackDamage(), playerMatchData.getDamageMitigated());
        return data;
    }

    /**
     * @param data is a data piece created by parsing through matches obtained from the Smite API
     */
    public DailyGodData incrementBans(DailyGodData data) {
        data.setBans(data.getBans() + 1);
        return data;
    }

    public void addMatchStats(DailyGodData data, int matchesPlayed, Integer damageDone,
                              Integer basicAttackDamageDone, Integer damageMitigated) {
        int incrementMatches = matchesPlayed + 1;
        data.setMatchesPlayed(incrementMatches);

        Integer damageScore = ((data.getAverageDamageDone() * matchesPlayed) + damageDone) / incrementMatches;
        data.setAverageDamageDone(damageScore);

        Integer basicAttackScores = ((data.getAverageBasicAttackDamage() * matchesPlayed) + basicAttackDamageDone) / incrementMatches;
        data.setAverageBasicAttackDamage(basicAttackScores);

        Integer damageMitigatedScores = ((data.getAverageDamageMitigated() * matchesPlayed) + damageMitigated) / incrementMatches;
        data.setAverageDamageMitigated(damageMitigatedScores);
    }

    public void incrementWins(DailyGodData data) {
        data.setWins(data.getWins() + 1);
    }

    public void addSkins(DailyGodData data, String skin) {
        Map<String, Integer> skins = data.getSkinsUsed();
        if (skins.containsKey(skin)) {
            skins.put(skin, skins.get(skin) + 1);
        } else {
            skins.put(skin, 1);
        }
    }

    public void addItems(DailyGodData data, List<String> playerItems) {
        Map<String, Integer> items = data.getPopularItems();
        for (String item : playerItems) {
            if (items.containsKey(item)) {
                items.put(item, items.get(item) + 1);
            } else {
                items.put(item, 1);
            }
        }
    }

    public void addActives(DailyGodData data, List<String> playerActives) {
        Map<String, Integer> actives = data.getPopularActives();
        for (String active : playerActives) {
            if (actives.containsKey(active)) {
                actives.put(active, actives.get(active) + 1);
            } else {
                actives.put(active, 1);
            }
        }
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

}
