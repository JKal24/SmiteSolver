package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.repository.LowMMRGodDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private LowMMRGodDataRepository lowMMRGodDataRepository;

    public void recordMMR(Float averageMMR) {
    }

    // Will be used to calculate total win rates, ban rates, etc.

    public void configureGodData(Float mmr, Integer godID, LocalDate date, List<String> popularItems, List<String> popularActives, Integer winStatus,
                                 Integer damageDone, Integer basicAttackDamage, Integer damageMitigated) {
        if(mmr < 1680.0) {
            if (winStatus == 1) {

            } else {

            }
            lowMMRGodDataRepository.findById(godID).ifPresentOrElse(data -> {

            }, null);

        } else {
            lowMMRGodDataRepository.findById(godID).ifPresentOrElse(data -> {

            }, null);

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
                lowMMRGodDataRepository.findById(godID).ifPresentOrElse(data -> {
                }, null);
            }
        }

    }

}
