package com.astro.SmiteSolver.controller;

import com.astro.SmiteSolver.entity.GodData;
import com.astro.SmiteSolver.entity.GodName;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.repository.GodPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GodDataController {

    @Autowired
    private GodPerformanceRepository performanceRepository;

    @Autowired
    private GodNameRepository godNameRepository;

    @GetMapping("/buildMatchData")
    public void buildMatchData() {

    }

    @GetMapping("/statistics/{god}")
    public @ResponseBody
    GodData getGodStatistics(@PathVariable String godName) {
        for (GodName godNames : godNameRepository.findAll()) {
            if (godNames.getGodName().equals(godName)) {
                return performanceRepository.findById(godNames.getGodID()).get();
            }
        }
        return null;
    }

}
