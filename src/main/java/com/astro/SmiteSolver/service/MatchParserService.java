package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.*;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.smitebasic.api.SmiteAPI;
import com.astro.smitebasic.api.Utils;
import com.astro.smitebasic.objects.characters.GodInfo;
import com.astro.smitebasic.objects.gamedata.PatchInfo;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private DataCompilationService dataCompilationService;

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
                                godDataLowMMRMap.put(bannedGodID, (DailyGodDataLowMMR) incrementBans(bannedGodData));

                            }
                        }
                    }
                    if (highMMR) {
                        matchCountHighMMR++;
                    } else {
                        matchCountLowMMR++;
                    }
                    averageMMRList = new ArrayList<>();
                    currentPlayerData = playerMatchData;
                    currentMatchID = currentPlayerData.getMatch();
                }

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
            }
        }
        dataCompilationService.compileGodData(godDataHighMMRMap, godDataLowMMRMap, matchCountHighMMR, matchCountLowMMR);
        dataCompilationService.configureMatchData(updateDate, matchCountHighMMR, matchCountLowMMR);
    }

    public List<PlayerMatchData> getDailyMultiMatchData(LocalDate date, int hour) {
        MatchInfo[] info = api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), date, hour);
        Integer[] matchIDs = Arrays.stream(info)
                .map(MatchInfo::getMatchID)
                .toArray(Integer[]::new);
        return api.getMultipleMatchData(matchIDs);
    }

    public List<PlayerMatchData> getDailyMultiMatchData(LocalDate date, int hour, int minutes) {
        Integer[] matchIDs = Arrays.stream(api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), date, hour, minutes))
                .map(MatchInfo::getMatchID)
                .toArray(Integer[]::new);
        return api.getMultipleMatchData(matchIDs);
    }

    public void updatePatch() {
        PatchInfo[] patchInfos = api.getPatchInfo();
        if (patchInfos == null)
            return;
        Double version = Utils.parseSingleEntry(patchInfos).getVersion();

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

    public <T extends DailyGodData> T configureGodData(PlayerMatchData playerMatchData, T data) {
        int matchesPlayed = data.getMatchesPlayed();
        int gameDuration = playerMatchData.getMatchDuration();

        if (getWinStatus(playerMatchData.getSideSelection(), playerMatchData.getWinningSide()) == 1) {
            incrementWins(data);
        }
        addSkins(data, playerMatchData.getSkin());
        addItems(data, getPlayerItems(playerMatchData));
        addActives(data, getPlayerActives(playerMatchData));
        addMatchStats(data, matchesPlayed, playerMatchData.getDamagePlayer(),
                playerMatchData.getBasicAttackDamage(), playerMatchData.getDamageMitigated(), gameDuration);
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
                              Integer basicAttackDamageDone, Integer damageMitigated, int gameDuration) {
        int incrementMatches = matchesPlayed + 1;
        data.setMatchesPlayed(incrementMatches);

        Integer damageScore = ((data.getAverageDPM() * matchesPlayed) + getPerMinuteStats(damageDone, gameDuration)) / incrementMatches;
        data.setAverageDPM(damageScore);

        Integer basicAttackScores = ((data.getAverageBasicAttackDPM() * matchesPlayed)
                + getPerMinuteStats(basicAttackDamageDone, gameDuration)) / incrementMatches;
        data.setAverageBasicAttackDPM(basicAttackScores);

        Integer damageMitigatedScores = ((data.getAverageDmgMitigatedPerMin() * matchesPlayed)
                + getPerMinuteStats(damageMitigated, gameDuration)) / incrementMatches;
        data.setAverageDmgMitigatedPerMin(damageMitigatedScores);
    }

    private int getPerMinuteStats(int val, int duration) {
        return (val / duration) * 60;
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
        data.setSkinsUsed(skins);
    }

    public void addItems(DailyGodData data, List<Item> playerItems) {
        Map<Item, Integer> items = data.getPopularItems();
        for (Item item : playerItems) {
            if (items.containsKey(item)) {
                items.put(item, items.get(item) + 1);
            } else {
                items.put(item, 1);
            }
        }
        data.setPopularItems(items);
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
        data.setPopularActives(actives);
    }

    private List<Item> getPlayerItems(PlayerMatchData data) {
        return Stream.of(new Item(data.getItemID1(), data.getItemPurch1()), new Item(data.getItemID2(), data.getItemPurch2()),
                new Item(data.getItemID3(), data.getItemPurch3()), new Item(data.getItemID4(), data.getItemPurch4()),
                new Item(data.getItemID5(), data.getItemPurch5()), new Item(data.getItemID6(), data.getItemPurch6()))
                .filter(entry -> !entry.getItemName().equals("")).collect(Collectors.toList());
    }

    private List<String> getPlayerActives(PlayerMatchData data) {
        return Stream.of(data.getItemActive1(), data.getItemActive2()).filter(entry -> !entry.equals("")).collect(Collectors.toList());
    }

    private List<Integer> getBannedGodIDs(PlayerMatchData data) {
        return Stream.of(data.getBan1ID(), data.getBan2ID(), data.getBan3ID(), data.getBan4ID(), data.getBan5ID(),
                data.getBan6ID(), data.getBan7ID(), data.getBan8ID(), data.getBan9ID(), data.getBan10ID())
                .filter(entry -> !entry.equals(0)).collect(Collectors.toList());
    }

    // Win represents 1, a loss represents 0
    private Integer getWinStatus(Integer taskForce, Integer winningTaskForce) {
        return taskForce.equals(winningTaskForce) ? 1 : 0;
    }

    private Float getMMRAverage(List<Float> averageMMR) {
        return averageMMR.stream().reduce(0.0F, Float::sum) / averageMMR.size();
    }

}
