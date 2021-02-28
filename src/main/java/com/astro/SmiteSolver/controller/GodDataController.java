package com.astro.SmiteSolver.controller;

import com.astro.SmiteSolver.entity.TotalGodData;
import com.astro.SmiteSolver.entity.GodName;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.repository.GodPerformanceRepository;
import com.astro.SmiteSolver.repository.HighMMRPerformanceRepository;
import com.astro.SmiteSolver.repository.LowMMRPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GodDataController {

    @Autowired
    private HighMMRPerformanceRepository highMMRPerformanceRepository;

    @Autowired
    private LowMMRPerformanceRepository lowMMRPerformanceRepository;

    @Autowired
    private GodNameRepository godNameRepository;

    @GetMapping("/buildMatchData")
    public void buildMatchData() {

    }

    @GetMapping("/statistics/{god}")
    public @ResponseBody
    TotalGodData getGodStatistics(@PathVariable String godName, @PathVariable boolean highMMR) {
        for (GodName godNames : godNameRepository.findAll()) {
            if (godNames.getGodName().equals(godName)) {
                return highMMR ? highMMRPerformanceRepository.findById(godNames.getGodID()).get() :
                        lowMMRPerformanceRepository.findById(godNames.getGodID()).get();
            }
        }
        return null;
    }

}
