package com.astro.SmiteSolver.data;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private DataService dataService;

    @Autowired
    private GodNamesRepository godNamesRepository;

    @PostConstruct
    private void initializeAPI() {
        api.setCredentials(apiUri, devID, authKey);
    }

    public void updateData() {
        updateService.getUpdatableDates().forEach(data -> {
            for(int parseHours = 0; parseHours < 24; parseHours++) {
                Integer[] matchIDs = Arrays.stream(api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), parseHours))
                        .map(MatchInfo::getMatchID)
                        .toArray(Integer[]::new);
                MultiMatchInfo multiMatchInfo = api.getMultipleMatchData(matchIDs);

                multiMatchInfo.getPlayerMatchDataList().forEach((matchID, matchInfo) -> {

                    List<Float> averageMMR = new ArrayList<>();

                    for (PlayerMatchData playerMatchData : matchInfo) {
                        dataService.configureGodData(playerMatchData.getRankStatConquest(), playerMatchData.getGodID(),
                                getPlayerItems(playerMatchData), getPlayerActives(playerMatchData),
                                getWinStatus(playerMatchData.getSideSelection(), playerMatchData.getWinningSide()),
                                playerMatchData.getDamagePlayer(), playerMatchData.getBasicAttackDamage(), playerMatchData.getDamageMitigated());

                        averageMMR.add(playerMatchData.getRankStatConquest());
                    }

                    // Replace bans list with a map for all matched matchID data...
                    dataService.configureMatchData(getMMRAverage(averageMMR), getBannedGodIDs(matchInfo[0]));

                });
                // Insert all data from multiMatchInfo
            }
        });
    }

    public void updateVersion() {
        String versionString = Utils.parseSingleEntry(api.getPatchInfo()).getVersion_string();
    }

    private boolean isUpdatableDate(LocalDate prevDate) {
        LocalDate currentDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"));
        return currentDate.minusDays(1).isAfter(prevDate);
    }

    private List<String> getPlayerItems(PlayerMatchData data) {
        return Arrays.asList(data.getItemPurch1(), data.getItemPurch2(), data.getItemPurch3(),
                data.getItemPurch4(), data.getItemPurch5(), data.getItemPurch6());
    }

    private List<String> getPlayerActives(PlayerMatchData data) {
        return Arrays.asList(data.getItemActive1(), data.getItemActive2());
    }

    private List<Integer> getBannedGodIDs(PlayerMatchData data) {
        return Arrays.asList(data.getBan1ID(), data.getBan2ID(), data.getBan3ID(), data.getBan4ID(), data.getBan5ID(),
                data.getBan6ID(), data.getBan7ID(), data.getBan8ID(), data.getBan9ID(), data.getBan10ID());
    }

    // Win represents 1, a loss represents 0
    private Integer getWinStatus(Integer taskForce, Integer winningTaskForce) {
        return taskForce == winningTaskForce ? 1 : 0;
    }

    private Float getMMRAverage(List<Float> averageMMR) {
        return averageMMR.stream().reduce(0.0F, (mmr1, mmr2) -> mmr1 + mmr2) / averageMMR.size();
    }
}
