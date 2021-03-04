package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.*;
import com.astro.SmiteSolver.repository.*;
import com.astro.smitebasic.objects.player.matches.PlayerMatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

@Service
public class PerformanceDataService {

    @Autowired
    private RecordedMatchRepository recordedMatchRepository;

    @Autowired
    private DailyLowMMRDailyGodDataRepository dailyLowMMRGodDataRepository;

    @Autowired
    private DailyHighMMRDailyGodDataRepository dailyHighMMRGodDataRepository;

    @Autowired
    private HighMMRPerformanceRepository highMMRPerformanceRepository;

    @Autowired
    private LowMMRPerformanceRepository lowMMRPerformanceRepository;

    @Autowired
    private GodNameRepository godNameRepository;

    @Autowired
    private UpdateService updateService;

    public void compileHighMMRPerformanceData() {
        for (GodName godName : godNameRepository.findAll()) {
            for (DailyGodDataHighMMR godDataHighMMR : dailyHighMMRGodDataRepository.findByGodID(godName.getGodID())) {

            }
        }
    }

    public void compileLowMMRPerformanceData() {

    }




    public void compileGodData(Map<Integer, DailyGodDataHighMMR> highMMRMap, Map<Integer, DailyGodDataLowMMR> lowMMRMap) {
        for (GodName godName : godNameRepository.findAll()) {
            int godID = godName.getGodID();

            DailyGodDataHighMMR dataHighMMR = highMMRMap.get(godID);
            DailyGodDataLowMMR dataLowMMR = lowMMRMap.get(godID);

            highMMRPerformanceRepository.findById(godID).ifPresentOrElse(godData -> {

                processGodData(dataHighMMR);
                deleteGodData();
            }, () -> {
                // God names should have been updated, if god ID not found then problem with API recording
            });

            lowMMRPerformanceRepository.findById(godID).ifPresentOrElse(godData -> {

                processGodData(dataLowMMR);
                deleteGodData();
            }, () -> {

            });


        }
    }

    public <T extends DailyGodData> void processGodData(T godData) {

    }

    public void deleteGodData() {
        LocalDate deletionDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(UpdateService.DATA_DELETION_DAY_LIMIT);
        LocalDate patchDate = updateService.getVersionUpdateDate();

        int totalMatchesHighMMR = 0;
        int totalMatchesLowMMR = 0;

        int newPatchMatchesHighMMR = 0;
        int newPatchMatchesLowMMR = 0;

        for (MatchRecordedData data : recordedMatchRepository.findAll()) {
            if (data.getDate().isBefore(deletionDate)) {
                recordedMatchRepository.delete(data);
            } else {
                totalMatchesHighMMR += data.getMatchesPlayedHighMMR();
                totalMatchesLowMMR += data.getMatchesPlayedLowMMR();
                if (data.getDate().isAfter(patchDate)) {
                    newPatchMatchesHighMMR += data.getMatchesPlayedHighMMR();
                    newPatchMatchesLowMMR += data.getMatchesPlayedLowMMR();
                }
            }
        }

        for (DailyGodDataHighMMR dataHighMMR : dailyHighMMRGodDataRepository.findAll()) {

            if (dataHighMMR.getDate().isBefore(deletionDate)) {

                int finalTotalMatchesHighMMR = totalMatchesHighMMR;
                int finalNewPatchMatchesHighMMR = newPatchMatchesHighMMR;

                highMMRPerformanceRepository.findById(dataHighMMR.getGodID()).ifPresentOrElse(godData -> {

                    highMMRPerformanceRepository.save(processDeletedGodData(godData, dataHighMMR, finalTotalMatchesHighMMR));

                    if (dataHighMMR.getDate().isBefore(patchDate)) {
                        highMMRPerformanceRepository.save(processNewPatchDeletedGodData(godData, dataHighMMR, finalNewPatchMatchesHighMMR));
                    }

                }, null);

                dailyHighMMRGodDataRepository.delete(dataHighMMR);
            }
        }

        for (DailyGodDataLowMMR dataLowMMR : dailyLowMMRGodDataRepository.findAll()) {

            if (dataLowMMR.getDate().isBefore(deletionDate)) {

                int finalTotalMatchesLowMMR = totalMatchesLowMMR;
                int finalNewPatchMatchesLowMMR = newPatchMatchesLowMMR;

                lowMMRPerformanceRepository.findById(dataLowMMR.getGodID()).ifPresentOrElse(godData -> {

                    lowMMRPerformanceRepository.save(processDeletedGodData(godData, dataLowMMR, finalTotalMatchesLowMMR));

                    if (dataLowMMR.getDate().isBefore(patchDate)) {
                        lowMMRPerformanceRepository.save(processNewPatchDeletedGodData(godData, dataLowMMR, finalNewPatchMatchesLowMMR));
                    }

                }, null);

                dailyLowMMRGodDataRepository.delete(dataLowMMR);
            }
        }
    }

    private <T extends TotalGodData, H extends DailyGodData> T processDeletedGodData(T godData, H dailyGodData, int totalMatches) {
        int totalMatchesPlayed = godData.getTotalMatchesPlayed();
        int matchesCut = dailyGodData.getMatchesPlayed();

        godData.setAverageBasicAttackDamage(calcDeletionAverageStat(godData.getAverageBasicAttackDamage(),
                dailyGodData.getAverageBasicAttackDamage(), totalMatchesPlayed, matchesCut));
        godData.setAverageDamageDone(calcDeletionAverageStat(godData.getAverageDamageDone(),
                dailyGodData.getAverageDamageDone(), totalMatchesPlayed, matchesCut));
        godData.setAverageDamageMitigated(calcDeletionAverageStat(godData.getAverageDamageMitigated(),
                dailyGodData.getAverageDamageMitigated(), totalMatchesPlayed, matchesCut));

        int numMatches = godData.getTotalMatchesPlayed() - dailyGodData.getMatchesPlayed();
        int numBans = godData.getTotalBans() - dailyGodData.getBans();
        int numWins = godData.getTotalWins() - dailyGodData.getWins();

        godData.setTotalMatchesPlayed(numMatches);
        godData.setTotalBans(numBans);
        godData.setTotalWins(numWins);

        godData.setMovingPickRate(new BigDecimal(numMatches / totalMatches));
        godData.setMovingBanRate(new BigDecimal(numBans / numMatches));
        godData.setMovingWinRate(new BigDecimal(numWins / numMatches));

        Map<String, Integer> skins = godData.getSkinsUsed();
        for (Map.Entry<String, Integer> skinsEntry : dailyGodData.getSkinsUsed().entrySet()) {
            String key = skinsEntry.getKey();
            skins.put(key, skins.getOrDefault(key, 0) + skinsEntry.getValue());
        }
        godData.setSkinsUsed(skins);

        godData.setPopularActives();
        godData.setPopularItems();

        return godData;
    }

    private <T extends TotalGodData, H extends DailyGodData> T processNewPatchDeletedGodData(T godData, H dailyGodData, int newPatchMatches) {
        int numNewPatchMatches = godData.getNewPatchMatchesPlayed() - dailyGodData.getMatchesPlayed();
        int numNewPatchBans = godData.getNewPatchBans() - dailyGodData.getBans();
        int numNewPatchWins = godData.getNewPatchWins() - dailyGodData.getWins();

        godData.setNewPatchMatchesPlayed(numNewPatchMatches);
        godData.setNewPatchBans(numNewPatchBans);
        godData.setNewPatchWins(numNewPatchWins);

        godData.setNewPatchPickRate(new BigDecimal(numNewPatchMatches / newPatchMatches));
        godData.setNewPatchBanRate(new BigDecimal(numNewPatchBans / numNewPatchMatches));
        godData.setNewPatchWinRate(new BigDecimal(numNewPatchWins / numNewPatchMatches));

        return godData;
    }

    private int calcDeletionAverageStat(int totalStat, int dailyStat, int totalMatchesPlayed, int matchesCut) {
        return (totalStat * totalMatchesPlayed - dailyStat * matchesCut) / totalMatchesPlayed - matchesCut;
    }

    private int calcInsertionAverageStat() {
        return 0;
    }

    public void configureHighMMRGodData(Map<Integer, DailyGodDataHighMMR> data) {
        for (DailyGodDataHighMMR godDataHighMMR : data.values()) {
            dailyHighMMRGodDataRepository.save(godDataHighMMR);
        }
    }

    public void configureLowMMRGodData(Map<Integer, DailyGodDataLowMMR> data) {
        for (DailyGodDataLowMMR godDataLowMMR : data.values()) {
            dailyLowMMRGodDataRepository.save(godDataLowMMR);
        }
    }

    public void configureMatchData(LocalDate date, Integer matchesPlayedHighMMR, Integer matchesPlayedLowMMR) {
        recordedMatchRepository.save(new MatchRecordedData(date, matchesPlayedHighMMR, matchesPlayedLowMMR));
    }

    private Integer makeDailyDataID(Integer godID, LocalDate date) {
        return date.getDayOfMonth() + date.getMonthValue() + date.getYear() + godID;
    }

}
