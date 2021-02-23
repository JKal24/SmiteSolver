package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.*;
import com.astro.SmiteSolver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

            }, () -> {

            });
        }
    }

    public void myData(GodData data, Map<Integer, DailyGodDataHighMMR> highMMRMap) {

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

}
