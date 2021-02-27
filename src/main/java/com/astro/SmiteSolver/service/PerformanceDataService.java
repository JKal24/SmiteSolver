package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.*;
import com.astro.SmiteSolver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;

@Service
public class PerformanceDataService {

    @Autowired
    private RecordedMatchRepository recordedMatchRepository;

    @Autowired
    private DailyLowMMRDailyGodDataRepository dailyLowMMRGodDataRepository;

    @Autowired
    private DailyHighMMRDailyGodDataRepository dailyHighMMRGodDataRepository;

    @Autowired
    private GodPerformanceRepository performanceRepository;

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

            performanceRepository.findById(godID).ifPresentOrElse(godData -> {



                deleteGodData();

            }, () -> {

            });
        }
    }

    public void deleteGodData() {
        LocalDate deletionDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(UpdateService.DATA_DELETION_DAY_LIMIT);

        for (DailyGodDataHighMMR dataHighMMR : dailyHighMMRGodDataRepository.findAll()) {
            if (dataHighMMR.getDate().isBefore(deletionDate)) {
                performanceRepository.findById(dataHighMMR.getGodID()).ifPresentOrElse(godData -> {

                    int totalMatchesPlayed = godData.getTotalMatchesPlayedHighMMR();
                    int matchesCut = dataHighMMR.getMatchesPlayed();

                    godData.setAverageBasicAttackDamageHighMMR(calcDeletionAverageStat(godData.getAverageBasicAttackDamageHighMMR(),
                            dataHighMMR.getAverageBasicAttackDamage(), totalMatchesPlayed, matchesCut));
                    godData.setAverageDamageDoneHighMMR(calcDeletionAverageStat(godData.getAverageDamageDoneHighMMR(),
                            dataHighMMR.getAverageDamageDone(), totalMatchesPlayed, matchesCut));

                }, null);

                dailyHighMMRGodDataRepository.delete(dataHighMMR);
            }
        }

        for (DailyGodDataLowMMR dataLowMMR : dailyLowMMRGodDataRepository.findAll()) {
            if (dataLowMMR.getDate().isBefore(deletionDate)) {
                performanceRepository.findById(dataLowMMR.getGodID()).ifPresentOrElse(godData -> {
                    int totalMatchesPlayed = godData.getTotalMatchesPlayedLowMMR();

                    godData.setAverageBasicAttackDamageHighMMR(calcDeletionAverageStat(godData.getAverageBasicAttackDamageLowMMR(),
                            dataLowMMR.getAverageBasicAttackDamage(), godData.getTotalMatchesPlayedLowMMR(), dataLowMMR.getMatchesPlayed()));
                }, null);

                dailyLowMMRGodDataRepository.delete(dataLowMMR);
            }
        }
    }

    private int calcDeletionAverageStat(int totalStat, int dailyStat, int totalMatchesPlayed, int matchesCut) {
        return (totalStat * totalMatchesPlayed - dailyStat * matchesCut) / totalMatchesPlayed - matchesCut;
    }

    private int calcInsertionAverageStat() {

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
