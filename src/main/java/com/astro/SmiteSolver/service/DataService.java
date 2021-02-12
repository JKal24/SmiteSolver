package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.repository.GodDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private GodDataRepository godDataRepository;

    public void recordMMR(Float averageMMR) {
    }

    public void configureGodData(Float mmr, Integer godID, List<String> popularItems, List<String> popularActives, Integer winStatus,
                                 Integer damageDone, Integer basicAttackDamage, Integer damageMitigated) {
        if(mmr < 1680.0) {
            if (winStatus == 1) {
                godDataRepository.incrementWinsLowMMR(godID);
            } else {
                godDataRepository.incrementLossesLowMMR(godID);
            }
            godDataRepository.findById(godID).ifPresentOrElse(data -> {
                data.setPopularItemsLowMMR();
                data.setBasicAttackDamageLowMMR();
            }, null);

        } else {
            godDataRepository.findById(godID).ifPresentOrElse(data ->);

        }
    }

    // https://evonsdesigns.medium.com/spring-jpa-one-to-many-query-examples-281078bc457b

    public void configureMatchData(Float averageMMR, List<Integer> bannedGodIDs) {
        // Work on making a custom exception for GodNotFound, make when update and god repos are fleshed out
        if(averageMMR < 1680.0) {
            for (Integer godID : bannedGodIDs) {
                godDataRepository.findById(godID).ifPresentOrElse(data -> {
                    data.setBansLowMMR(data.getBansLowMMR() + 1);
                }, null);
            }
        } else {
            for (Integer godID : bannedGodIDs) {
                godDataRepository.findById(godID).ifPresentOrElse(data -> {
                    data.setBansHighMMR(data.getBansHighMMR() + 1);
                }, null);
            }
        }

    }

    @Query("UPDATE ")
    private void incrementUses() {

    }

}
