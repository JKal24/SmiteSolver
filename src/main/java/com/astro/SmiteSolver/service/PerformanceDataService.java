package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.*;
import com.astro.SmiteSolver.repository.*;
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



                deleteGodData();

            }, () -> {

            });


        }
    }

    public void deleteGodData() {
        LocalDate deletionDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(UpdateService.DATA_DELETION_DAY_LIMIT);

        for (DailyGodDataHighMMR dataHighMMR : dailyHighMMRGodDataRepository.findAll()) {

            if (dataHighMMR.getDate().isBefore(deletionDate)) {

                highMMRPerformanceRepository.findById(dataHighMMR.getGodID()).ifPresentOrElse(godData -> {

                    int totalMatchesPlayed = godData.getTotalMatchesPlayed();
                    int matchesCut = dataHighMMR.getMatchesPlayed();

                    godData.setAverageBasicAttackDamage(calcDeletionAverageStat(godData.getAverageBasicAttackDamage(),
                            dataHighMMR.getAverageBasicAttackDamage(), totalMatchesPlayed, matchesCut));
                    godData.setAverageDamageDone(calcDeletionAverageStat(godData.getAverageDamageDone(),
                            dataHighMMR.getAverageDamageDone(), totalMatchesPlayed, matchesCut));
                    godData.setAverageDamageMitigated(calcDeletionAverageStat(godData.getAverageDamageMitigated(),
                            dataHighMMR.getAverageDamageMitigated(), totalMatchesPlayed, matchesCut));

                }, null);

                dailyHighMMRGodDataRepository.delete(dataHighMMR);
            }
        }

        for (DailyGodDataLowMMR dataLowMMR : dailyLowMMRGodDataRepository.findAll()) {
            if (dataLowMMR.getDate().isBefore(deletionDate)) {
                lowMMRPerformanceRepository.findById(dataLowMMR.getGodID()).ifPresentOrElse(godData -> {
                    int totalMatchesPlayed = godData.getTotalMatchesPlayed();

                    godData.setAverageBasicAttackDamage(calcDeletionAverageStat(godData.getAverageBasicAttackDamage(),
                            dataLowMMR.getAverageBasicAttackDamage(), godData.getTotalMatchesPlayed(), dataLowMMR.getMatchesPlayed()));
                }, null);

                dailyLowMMRGodDataRepository.delete(dataLowMMR);
            }
        }
    }

    private <T extends TotalGodData, H extends DailyGodData> T processDeletedGodData(T godData, H dailyGodData) {
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

        godData.setMovingBanRate(new BigDecimal(numBans / numMatches));

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

    public void configureMatchData(LocalDate date, Integer matchesPlayed) {
        recordedMatchRepository.save(new MatchRecordedData(date, matchesPlayed));
    }

    private Integer makeDailyDataID(Integer godID, LocalDate date) {
        return date.getDayOfMonth() + date.getMonthValue() + date.getYear() + godID;
    }

}
