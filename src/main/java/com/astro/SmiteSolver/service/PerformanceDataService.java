package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.God.GodData;
import com.astro.SmiteSolver.entity.God.GodDataHighMMR;
import com.astro.SmiteSolver.entity.God.GodDataLowMMR;
import com.astro.SmiteSolver.repository.HighMMRGodDataRepository;
import com.astro.SmiteSolver.repository.LowMMRGodDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PerformanceDataService {

    @Autowired
    private LowMMRGodDataRepository lowMMRGodDataRepository;

    @Autowired
    private HighMMRGodDataRepository highMMRGodDataRepository;

    // Will be used to calculate total win rates, ban rates, etc.

    public void configureGodData(GodData data, Float averageMMR) {
        if(averageMMR < 1680.0) {
            lowMMRGodDataRepository.save((GodDataLowMMR) data);
        } else {
            highMMRGodDataRepository.save((GodDataHighMMR) data);
        }
    }

    public void configureMatchData(Float averageMMR, List<Integer> bannedGodIDs) {
        // Work on making a custom exception for GodNotFound, make when update and god repos are fleshed out
        if(averageMMR < 1680.0) {
            for (Integer godID : bannedGodIDs) {
                lowMMRGodDataRepository.findById(godID).ifPresentOrElse(data -> {
                }, null);
            }
        } else {
            for (Integer godID : bannedGodIDs) {
                highMMRGodDataRepository.findById(godID).ifPresentOrElse(data -> {
                }, null);
            }
        }

    }

}
