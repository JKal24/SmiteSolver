package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.God.GodDataLowMMR;
import com.astro.SmiteSolver.repository.LowMMRGodDataRepository;
import com.astro.SmiteSolver.repository.GodNamesRepository;
import com.astro.smitebasic.api.SmiteAPI;
import com.astro.smitebasic.api.Utils;
import com.astro.smitebasic.objects.gamedata.matches.MatchInfo;
import com.astro.smitebasic.objects.gamedata.matches.MultiMatchInfo;
import com.astro.smitebasic.objects.player.matches.PlayerMatchData;
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
public class PerformanceDataService {

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
    private DataService dataService;

    @Autowired
    private LowMMRGodDataRepository lowMMRGodDataRepository;

    @Autowired
    private GodNamesRepository godNamesRepository;

    @PostConstruct
    private void initializeAPI() {
        api.setCredentials(apiUri, devID, authKey);
    }

    public void updateData() {
        updateService.getUpdatableDates().forEach(date -> {
            Map<Integer, GodDataLowMMR> godDataMap = new HashMap<>();

            for(int parseHours = 0; parseHours < 24; parseHours++) {
                Integer[] matchIDs = Arrays.stream(api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), date, parseHours))
                        .map(MatchInfo::getMatchID)
                        .toArray(Integer[]::new);
                MultiMatchInfo multiMatchInfo = api.getMultipleMatchData(matchIDs);

                multiMatchInfo.getPlayerMatchDataList().forEach((matchID, matchInfo) -> {

                    List<Float> averageMMR = new ArrayList<>();

                    for (PlayerMatchData playerMatchData : matchInfo) {

                        Integer key = makeDataID(date, playerMatchData.getGodID());
                        if (godDataMap.containsKey(key)) {
                            godDataMap.put(key, configureGodData(playerMatchData, godDataMap.get(key)));
                        } else {
                            godDataMap.put(key, configureGodData(playerMatchData, new GodDataLowMMR()));
                        }

                        dataService.configureGodData(playerMatchData.getRankStatConquest(), playerMatchData.getGodID(), date,
                                getPlayerItems(playerMatchData), getPlayerActives(playerMatchData),
                                getWinStatus(playerMatchData.getSideSelection(), playerMatchData.getWinningSide()),
                                playerMatchData.getDamagePlayer(), playerMatchData.getBasicAttackDamage(), playerMatchData.getDamageMitigated());

                        averageMMR.add(playerMatchData.getRankStatConquest());
                    }

                    dataService.recordMMR(getMMRAverage(averageMMR));
                    // Replace bans list with a map for all matched matchID data...
                    dataService.configureMatchData(getMMRAverage(averageMMR), getBannedGodIDs(matchInfo[0]));

                });
                // Insert all data from multiMatchInfo
            }
        });
    }

    public GodDataLowMMR configureGodData(PlayerMatchData playerMatchData, GodDataLowMMR data) {
        if (getWinStatus(playerMatchData.getSideSelection(), playerMatchData.getWinningSide()) == 1) {
            incrementWins(data);
        }
        incrementMatchesPlayed(data);
        return new GodDataLowMMR();
    }

    public GodDataLowMMR configureBanData(List<Integer> bannedGodIDs) {

    }

    /**
     * @param data is a data piece created by parsing through matches obtained from the Smite API
     * @return the same data piece but with incremented data
     */

    // Will create an interface which encompasses both GodData entities

    public GodDataLowMMR incrementMatchesPlayed(GodDataLowMMR data) {
        data.setMatchesPlayed(data.getMatchesPlayed() + 1);
        return data;
    }

    public GodDataLowMMR incrementWins(GodDataLowMMR data) {
        data.setWins(data.getWins() + 1);
        return data;
    }

    public GodDataLowMMR incrementBans(GodDataLowMMR data) {
        return data;
    }

    public GodDataLowMMR addSkins(GodDataLowMMR data, List<String> playerSkins) {
        return data;
    }

    public GodDataLowMMR addItems(GodDataLowMMR data, List<String> playerItems) {
        return data;
    }

    public GodDataLowMMR addActives(GodDataLowMMR data, List<String> playerActives) {
        return data;
    }

    public GodDataLowMMR addDamageStats(GodDataLowMMR data, Integer damageDone, Integer basicAttackDamageDone, Integer damageMitigated) {
        return data;
    }

    public void updateVersion() {
        String versionString = Utils.parseSingleEntry(api.getPatchInfo()).getVersion_string();
    }

    private boolean isUpdatableDate(LocalDate prevDate) {
        LocalDate currentDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"));
        return currentDate.minusDays(1).isAfter(prevDate);
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
}
