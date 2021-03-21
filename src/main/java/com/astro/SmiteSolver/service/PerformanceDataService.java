package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.*;
import com.astro.SmiteSolver.exception.GodNotFoundException;
import com.astro.SmiteSolver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public void compileGodData(Map<Integer, DailyGodDataHighMMR> highMMRMap, Map<Integer, DailyGodDataLowMMR> lowMMRMap, int highMMRMatchesCount, int lowMMRMatchesCount) {
        // Gets the totalMatches played for high and low mmr and filters by deletion date and new patch date

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

        deleteHighMMRGodData(deletionDate, patchDate, totalMatchesHighMMR, newPatchMatchesHighMMR);
        deleteLowMMRData(deletionDate, patchDate, totalMatchesLowMMR, newPatchMatchesLowMMR);

        for (GodName godName : godNameRepository.findAll()) {
            int godID = godName.getGodID();

            DailyGodDataHighMMR dataHighMMR = highMMRMap.getOrDefault(godID,
                    new DailyGodDataHighMMR(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")), godID, godName.getGodName()));
            DailyGodDataLowMMR dataLowMMR = lowMMRMap.getOrDefault(godID,
                    new DailyGodDataLowMMR(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")), godID, godName.getGodName()));

            dailyHighMMRGodDataRepository.save(dataHighMMR);
            dailyLowMMRGodDataRepository.save(dataLowMMR);

            addHighMMRGodData(godID, dataHighMMR,  totalMatchesHighMMR + highMMRMatchesCount,
                    newPatchMatchesHighMMR + highMMRMatchesCount);

            addLowMMRGodData(godID, dataLowMMR, totalMatchesLowMMR + lowMMRMatchesCount,
                    newPatchMatchesLowMMR + lowMMRMatchesCount);
        }
    }

    public void addHighMMRGodData(int godID, DailyGodDataHighMMR dataHighMMR, int totalMatches, int newPatchMatches) {
        highMMRPerformanceRepository.findById(godID).ifPresentOrElse(godData -> {
            highMMRPerformanceRepository.save(processAddedGodData(godData, dataHighMMR, totalMatches, newPatchMatches));
        }, () -> godNameRepository.findById(godID).ifPresentOrElse(name ->
                highMMRPerformanceRepository.save(new TotalGodDataHighMMR(godID, name.getGodName())),
                () -> {
            throw new GodNotFoundException(String.format("Could not find appropriate data for the god ID: %d", godID));
        }));
    }

    public void addLowMMRGodData(int godID, DailyGodDataLowMMR dataLowMMR, int totalMatches, int newPatchMatches) {
        lowMMRPerformanceRepository.findById(godID).ifPresentOrElse(godData -> {
            lowMMRPerformanceRepository.save(processAddedGodData(godData, dataLowMMR, totalMatches, newPatchMatches));
        }, () -> godNameRepository.findById(godID).ifPresentOrElse(name ->
                lowMMRPerformanceRepository.save(new TotalGodDataLowMMR(godID, name.getGodName())),
                () -> {
            throw new GodNotFoundException(String.format("Could not find appropriate data for the god ID: %d", godID));
        }));
    }

    public void deleteHighMMRGodData(LocalDate deletionDate, LocalDate patchDate, int totalMatches, int newPatchTotalMatches) {
        for (DailyGodDataHighMMR dataHighMMR : dailyHighMMRGodDataRepository.findAll()) {

            if (dataHighMMR.getDate().isBefore(deletionDate)) {

                highMMRPerformanceRepository.findById(dataHighMMR.getGodID()).ifPresentOrElse(godData -> {

                    highMMRPerformanceRepository.save(processDeletedGodData(godData, dataHighMMR, totalMatches));

                    if (dataHighMMR.getDate().isBefore(patchDate)) {
                        highMMRPerformanceRepository.save(processNewPatchDeletedGodData(godData, dataHighMMR, newPatchTotalMatches));
                    }

                }, null);

                dailyHighMMRGodDataRepository.delete(dataHighMMR);
            }
        }


    }

    public void deleteLowMMRData(LocalDate deletionDate, LocalDate patchDate, int totalMatches, int newPatchTotalMatches) {
        for (DailyGodDataLowMMR dataLowMMR : dailyLowMMRGodDataRepository.findAll()) {

            if (dataLowMMR.getDate().isBefore(deletionDate)) {

                lowMMRPerformanceRepository.findById(dataLowMMR.getGodID()).ifPresentOrElse(godData -> {

                    lowMMRPerformanceRepository.save(processDeletedGodData(godData, dataLowMMR, totalMatches));

                    if (dataLowMMR.getDate().isBefore(patchDate)) {
                        lowMMRPerformanceRepository.save(processNewPatchDeletedGodData(godData, dataLowMMR, newPatchTotalMatches));
                    }

                }, null);

                dailyLowMMRGodDataRepository.delete(dataLowMMR);
            }
        }
    }


    private <T extends TotalGodData, H extends DailyGodData> T processAddedGodData(T godData, H dailyGodData, int totalMatches, int newPatchMatches) {
        int totalMatchesPlayed = godData.getTotalMatchesPlayed();
        int matchesAdded = dailyGodData.getMatchesPlayed();

        godData.setAverageBasicAttackDamage(calcAdditionAverageStat(godData.getAverageBasicAttackDamage(), dailyGodData.getAverageBasicAttackDPM(),
                totalMatchesPlayed, matchesAdded));
        godData.setAverageDamageDone(calcAdditionAverageStat(godData.getAverageDamageDone(), dailyGodData.getAverageDPM(),
                totalMatchesPlayed, matchesAdded));
        godData.setAverageDamageMitigated(calcAdditionAverageStat(godData.getAverageDamageMitigated(), dailyGodData.getAverageDmgMitigatedPerMin(),
                totalMatchesPlayed, matchesAdded));

        int numMatches = utils.roundZero(totalMatchesPlayed + matchesAdded);
        int newPatchNumMatches = godData.getNewPatchMatchesPlayed() + matchesAdded;

        int bans = dailyGodData.getBans();
        int numBans = godData.getTotalBans() + bans;
        int newPatchNumBans = godData.getNewPatchBans() + bans;

        int wins = dailyGodData.getWins();
        int numWins = godData.getTotalWins() + wins;
        int newPatchNumWins = godData.getNewPatchWins() + wins;

        godData.setTotalMatchesPlayed(numMatches);
        godData.setNewPatchMatchesPlayed(newPatchNumMatches);

        godData.setTotalBans(numBans);
        godData.setNewPatchBans(newPatchNumBans);

        godData.setTotalWins(numWins);
        godData.setNewPatchWins(newPatchNumWins);

        godData.setMovingPickRate(new BigDecimal(numMatches / utils.roundZero(totalMatches)));
        godData.setNewPatchPickRate(calcAdditionPercentageStat(godData.getNewPatchPickRate(), matchesAdded, totalMatchesPlayed, newPatchMatches));

        godData.setMovingBanRate(new BigDecimal(numBans / numMatches));
        godData.setNewPatchBanRate(calcAdditionPercentageStat(godData.getNewPatchBanRate(), dailyGodData.getWins(), totalMatchesPlayed, matchesAdded));

        godData.setMovingWinRate(new BigDecimal(numWins / numMatches));
        godData.setNewPatchWinRate(calcAdditionPercentageStat(godData.getNewPatchWinRate(), dailyGodData.getWins(), totalMatchesPlayed, matchesAdded));

        godData.setSkinsUsed(addNameCountMap(godData.getSkinsUsed(), dailyGodData.getSkinsUsed().entrySet()));
        godData.setPopularActives(addNameCountMap(godData.getPopularActives(), dailyGodData.getPopularActives().entrySet()));
        godData.setPopularItems(addNameCountMap(godData.getPopularItems(), dailyGodData.getPopularItems().entrySet()));
        godData.setNewPatchPopularItems(addNameCountMap(godData.getNewPatchPopularItems(), dailyGodData.getPopularItems().entrySet()));

        return godData;
    }

    private <T extends TotalGodData, H extends DailyGodData> T processDeletedGodData(T godData, H dailyGodData, int totalMatches) {
        int totalMatchesPlayed = utils.roundZero(godData.getTotalMatchesPlayed());
        int matchesCut = dailyGodData.getMatchesPlayed();

        godData.setAverageBasicAttackDamage(calcDeletionAverageStat(godData.getAverageBasicAttackDamage(),
                dailyGodData.getAverageBasicAttackDPM(), totalMatchesPlayed, matchesCut));
        godData.setAverageDamageDone(calcDeletionAverageStat(godData.getAverageDamageDone(),
                dailyGodData.getAverageDPM(), totalMatchesPlayed, matchesCut));
        godData.setAverageDamageMitigated(calcDeletionAverageStat(godData.getAverageDamageMitigated(),
                dailyGodData.getAverageDmgMitigatedPerMin(), totalMatchesPlayed, matchesCut));

        int numMatches = utils.roundZero(totalMatchesPlayed - matchesCut);
        int numBans = godData.getTotalBans() - dailyGodData.getBans();
        int numWins = godData.getTotalWins() - dailyGodData.getWins();

        godData.setTotalMatchesPlayed(numMatches);
        godData.setTotalBans(numBans);
        godData.setTotalWins(numWins);

        godData.setMovingPickRate(new BigDecimal(numMatches / totalMatches));
        godData.setMovingBanRate(new BigDecimal(numBans / numMatches));
        godData.setMovingWinRate(new BigDecimal(numWins / numMatches));

        godData.setSkinsUsed(removeNameCountMap(godData.getSkinsUsed(), dailyGodData.getSkinsUsed().entrySet()));
        godData.setPopularActives(removeNameCountMap(godData.getPopularActives(), dailyGodData.getPopularActives().entrySet()));
        godData.setPopularItems(removeNameCountMap(godData.getPopularItems(), dailyGodData.getPopularItems().entrySet()));

        return godData;
    }

    private <T extends TotalGodData, H extends DailyGodData> T processNewPatchDeletedGodData(T godData, H dailyGodData, int newPatchMatches) {
        int numNewPatchMatches = utils.roundZero(godData.getNewPatchMatchesPlayed() - dailyGodData.getMatchesPlayed());
        int numNewPatchBans = godData.getNewPatchBans() - dailyGodData.getBans();
        int numNewPatchWins = godData.getNewPatchWins() - dailyGodData.getWins();

        godData.setNewPatchMatchesPlayed(numNewPatchMatches);
        godData.setNewPatchBans(numNewPatchBans);
        godData.setNewPatchWins(numNewPatchWins);

        godData.setNewPatchPickRate(new BigDecimal(numNewPatchMatches / newPatchMatches));
        godData.setNewPatchBanRate(new BigDecimal(numNewPatchBans / numNewPatchMatches));
        godData.setNewPatchWinRate(new BigDecimal(numNewPatchWins / numNewPatchMatches));

        godData.setNewPatchPopularItems(removeNameCountMap(godData.getNewPatchPopularItems(), dailyGodData.getPopularItems().entrySet()));

        return godData;
    }

    private int calcDeletionAverageStat(int totalStat, int dailyStat, int totalMatchesPlayed, int matchesCut) {
        int newTotalMatches = utils.roundZero(totalMatchesPlayed - matchesCut);
        return (totalStat * totalMatchesPlayed - dailyStat * matchesCut) / newTotalMatches;
    }

    private int calcAdditionAverageStat(int totalStat, int dailyStat, int totalMatchesPlayed, int matchesAdded) {
        int newTotalMatches = utils.roundZero(totalMatchesPlayed + matchesAdded);
        return (totalStat * totalMatchesPlayed + dailyStat * matchesAdded) / newTotalMatches;
    }

    private BigDecimal calcAdditionPercentageStat(BigDecimal totalStat, int dailyTotal, int totalMatchesPlayed, int matchesAdded) {
        return (totalStat.multiply(BigDecimal.valueOf(totalMatchesPlayed)).add(BigDecimal.valueOf(dailyTotal)))
                .divide(BigDecimal.valueOf(totalMatchesPlayed + matchesAdded), RoundingMode.CEILING);
    }

    public void configureMatchData(LocalDate date, Integer matchesPlayedHighMMR, Integer matchesPlayedLowMMR) {
        recordedMatchRepository.save(new MatchRecordedData(date, matchesPlayedHighMMR, matchesPlayedLowMMR));
    }

    public List<TotalGodDataHighMMR> getTotalHighMMRData() {
        return (List<TotalGodDataHighMMR>) highMMRPerformanceRepository.findAll();
    }

    public List<TotalGodDataLowMMR> getTotalLowMMRData() {
        return (List<TotalGodDataLowMMR>) lowMMRPerformanceRepository.findAll();
    }

    private <T> Map<T, Integer> removeNameCountMap(Map<T, Integer> map, Set<Map.Entry<T, Integer>> entrySet) {
        for (Map.Entry<T, Integer> entry : entrySet) {
            T key = entry.getKey();
            int value = map.getOrDefault(key, 0) - entry.getValue();
            map.put(key, Math.max(value, 0));
        }
        return map;
    }

    private <T> Map<T, Integer> addNameCountMap(Map<T, Integer> map, Set<Map.Entry<T, Integer>> entrySet) {
        for (Map.Entry<T, Integer> entry : entrySet) {
            T key = entry.getKey();
            map.put(key, map.getOrDefault(key, 0) + entry.getValue());
        }
        return map;
    }

}
