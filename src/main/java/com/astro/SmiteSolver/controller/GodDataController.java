package com.astro.SmiteSolver.controller;

import com.astro.SmiteSolver.object.GodStatistics;
import com.astro.SmiteSolver.object.GodNames;
import com.astro.SmiteSolver.repository.GodDataRepository;
import com.astro.SmiteSolver.repository.GodNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GodDataController {

    @Autowired
    private GodDataRepository godStatisticsRepository;

    @Autowired
    private GodNamesRepository godNamesRepository;

    @GetMapping("/buildMatchData")
    public void buildMatchData() {

    }

    @GetMapping("/statistics/{god}")
    public @ResponseBody
    GodStatistics getGodStatistics(@PathVariable String godName) {
        for (GodNames godNames : godNamesRepository.findAll()) {
            if (godNames.getGodName().equals(godName)) {
                return godStatisticsRepository.findById(godNames.getGodID()).get();
            }
        }
        return null;
    }

}
