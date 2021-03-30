package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.TotalGodData;
import com.astro.SmiteSolver.entity.TotalGodDataHighMMR;
import com.astro.SmiteSolver.entity.TotalGodDataLowMMR;
import com.astro.SmiteSolver.exception.GodNotFoundException;
import com.astro.SmiteSolver.repository.HighMMRPerformanceRepository;
import com.astro.SmiteSolver.repository.LowMMRPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceDataService {

    @Autowired
    private HighMMRPerformanceRepository highMMRPerformanceRepository;

    @Autowired
    private LowMMRPerformanceRepository lowMMRPerformanceRepository;

    public <T extends TotalGodData> T getPerformanceData(int godID, boolean highMMR) {
        if (highMMR) {
            for (TotalGodDataHighMMR totalGodDataHighMMR : highMMRPerformanceRepository.findAll()) {
                if (godID == totalGodDataHighMMR.getGodID()) {
                    return (T) totalGodDataHighMMR;
                }
            }
            throw new GodNotFoundException(String.format("Could not find the given god for the ID, %s", godID));

        } else {
            for (TotalGodDataLowMMR totalGodDataLowMMR : lowMMRPerformanceRepository.findAll()) {
                if (godID == totalGodDataLowMMR.getGodID()) {
                    return (T) totalGodDataLowMMR;
                }
            }
            throw new GodNotFoundException(String.format("Could not find the given god for the ID, %s", godID));

        }
    }
}
