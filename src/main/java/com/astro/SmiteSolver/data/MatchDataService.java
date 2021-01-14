package com.astro.SmiteSolver.data;

import com.astro.SmiteSolver.repository.UpdateRepository;
import com.astro.smitebasic.api.SmiteAPI;
import com.astro.smitebasic.api.Utils;
import com.astro.smitebasic.objects.gamedata.matches.MatchInfo;
import com.astro.smitebasic.objects.gamedata.matches.MultiMatchInfo;
import com.astro.smitebasic.utils.Mode;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

public class MatchDataService {

    @Autowired
    SmiteAPI api;

    @Autowired
    UpdateRepository updateRepository;

    public void updateData() {
        updateRepository.findAll().forEach(updateData -> {

            if(isUpdatableDate(updateData.getUpdatedDate())) {

                for(int parseHours = 0; parseHours < 24; parseHours++) {
                    Integer[] matchIDs = Arrays.stream(api.getMatchIDs(Mode.CONQUEST_LEAGUE.getModeID(), parseHours))
                            .map(MatchInfo::getMatchID)
                            .toArray(Integer[]::new);
                    MultiMatchInfo multiMatchInfo = api.getMultipleMatchData(matchIDs);

                    // Insert all data from multiMatchInfo
                }

            }

        });
    }

    public void updateVersion() {
        updateRepository.findAll().forEach(updateData -> {

            String versionString = Utils.parseSingleEntry(api.getPatchInfo()).getVersion_string();
            if(Float.parseFloat(versionString) != updateData.getVersion()) {
                // Update entire database, look for new gods, reset win rate data, etc.
            }

        });
    }

    private boolean isUpdatableDate(LocalDate prevDate) {
        LocalDate currentDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"));
        return currentDate.minusDays(1).isAfter(prevDate);
    }
}
