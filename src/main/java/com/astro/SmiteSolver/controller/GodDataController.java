package com.astro.SmiteSolver.controller;

import com.astro.SmiteSolver.entity.GodNames;
import com.astro.SmiteSolver.entity.PerformanceDataLowMMR;
import com.astro.SmiteSolver.repository.PerformanceHighMMRRepository;
import com.astro.SmiteSolver.repository.PerformanceLowMMRRepository;
import com.astro.SmiteSolver.repository.GodNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GodDataController {

    @Autowired
    private PerformanceLowMMRRepository performanceLowMMRRepository;

    @Autowired
    private PerformanceHighMMRRepository performanceHighMMRRepository;

    @Autowired
    private GodNamesRepository godNamesRepository;

    @GetMapping("/buildMatchData")
    public void buildMatchData() {

    }

    @GetMapping("/statistics/{god}")
    public @ResponseBody
    PerformanceDataLowMMR getGodStatistics(@PathVariable String godName) {
        for (GodNames godNames : godNamesRepository.findAll()) {
            if (godNames.getGodName().equals(godName)) {
                return performanceLowMMRRepository.findById(godNames.getGodID()).get();
            }
        }
        return null;
    }

}
