package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.God.GodDataHighMMR;
import com.astro.SmiteSolver.entity.God.GodDataLowMMR;
import com.astro.SmiteSolver.repository.HighMMRGodDataRepository;
import com.astro.SmiteSolver.repository.LowMMRGodDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PerformanceDataService {

    @Autowired
    private LowMMRGodDataRepository lowMMRGodDataRepository;

    @Autowired
    private HighMMRGodDataRepository highMMRGodDataRepository;

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

    public void configureMatchData(Integer matchesPlayed) {

    }

}
