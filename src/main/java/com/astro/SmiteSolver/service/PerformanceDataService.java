package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.GodDataHighMMR;
import com.astro.SmiteSolver.entity.GodDataLowMMR;
import com.astro.SmiteSolver.entity.MatchData;
import com.astro.SmiteSolver.repository.HighMMRGodDataRepository;
import com.astro.SmiteSolver.repository.LowMMRGodDataRepository;
import com.astro.SmiteSolver.repository.MatchDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class PerformanceDataService {

    @Autowired
    private LowMMRGodDataRepository lowMMRGodDataRepository;

    @Autowired
    private HighMMRGodDataRepository highMMRGodDataRepository;

    @Autowired
    private MatchDataRepository matchDataRepository;

    public void configureHighMMRGodData(Map<Integer, GodDataHighMMR> data) {
        for (GodDataHighMMR godDataHighMMR : data.values()) {
            highMMRGodDataRepository.save(godDataHighMMR);
        }
    }

    public void configureLowMMRGodData(Map<Integer, GodDataLowMMR> data) {
        for (GodDataLowMMR godDataLowMMR : data.values()) {
            lowMMRGodDataRepository.save(godDataLowMMR);
        }
    }

    public void configureMatchData(LocalDate date, Integer matchesPlayed) {
        matchDataRepository.save(new MatchData(date, matchesPlayed));
    }

}
