package com.astro.SmiteSolver.data;

import com.astro.SmiteSolver.repository.UpdateRepository;
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
import java.util.Arrays;

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
    private UpdateRepository updateRepository;

    @Autowired
    private GodDataService godDataService;

    @PostConstruct
    private void initializeAPI() {
        api.setCredentials(apiUri, devID, authKey);
    }

    public void updateData() {
        updateRepository.findAll().forEach(data -> {

            if(isUpdatableDate(data.getUpdatedDate())) {

                LocalDate date = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"));

                for(int parseHours = 0; parseHours < 24; parseHours++) {
                    Integer[] matchIDs = Arrays.stream(api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), parseHours))
                            .map(MatchInfo::getMatchID)
                            .toArray(Integer[]::new);
                    MultiMatchInfo multiMatchInfo = api.getMultipleMatchData(matchIDs);

                    multiMatchInfo.getPlayerMatchDataList().forEach((matchID, matchInfo) -> {

                        for(int parseMatch = 0; parseMatch < matchInfo.length; parseMatch++) {
                            PlayerMatchData playerMatchData = matchInfo[parseMatch];


                        }

                    });
                    // Insert all data from multiMatchInfo
                }

            }

        });
    }

    public void updateVersion() {
        updateRepository.findAll().forEach(data -> {

            String versionString = Utils.parseSingleEntry(api.getPatchInfo()).getVersion_string();
            if(Float.parseFloat(versionString) != data.getVersion()) {
                // Update entire database, look for new gods, reset win rate data, etc.
            }
        });
    }

    private boolean isUpdatableDate(LocalDate prevDate) {
        LocalDate currentDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"));
        return currentDate.minusDays(1).isAfter(prevDate);
    }
}
