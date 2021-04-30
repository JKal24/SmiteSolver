package com.astro.service;

import com.astro.entity.TotalGodData;
import com.astro.entity.TotalGodDataHighMMR;
import com.astro.entity.TotalGodDataLowMMR;
import com.astro.exception.GodNotFoundException;
import com.astro.repository.HighMMRPerformanceRepository;
import com.astro.repository.LowMMRPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataFetchingService {

    @Autowired
    private HighMMRPerformanceRepository highMMRPerformanceRepository;

    @Autowired
    private LowMMRPerformanceRepository lowMMRPerformanceRepository;

    @Autowired
    private MatchParserService matchParserService;

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

    public void requestUpdate(int numDays) {
        matchParserService.updateData(numDays);
    }
}
