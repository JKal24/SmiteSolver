package com.astro.SmiteSolver.controller;

import com.astro.SmiteSolver.object.PerformanceData;
import com.astro.SmiteSolver.object.God.GodNames;
import com.astro.SmiteSolver.repository.PerformanceDataRepository;
import com.astro.SmiteSolver.repository.GodNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GodDataController {

    @Autowired
    private PerformanceDataRepository performanceDataRepository;

    @Autowired
    private GodNamesRepository godNamesRepository;

    @GetMapping("/buildMatchData")
    public void buildMatchData() {

    }

    @GetMapping("/statistics/{god}")
    public @ResponseBody
    PerformanceData getGodStatistics(@PathVariable String godName) {
        for (GodNames godNames : godNamesRepository.findAll()) {
            if (godNames.getGodName().equals(godName)) {
                return performanceDataRepository.findById(godNames.getGodID()).get();
            }
        }
        return null;
    }

}
