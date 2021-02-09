package com.astro.SmiteSolver.data;

import com.astro.SmiteSolver.object.GodData;
import com.astro.SmiteSolver.object.RankStatistics;
import com.astro.SmiteSolver.repository.GodDataRepository;
import com.astro.SmiteSolver.repository.RankStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private RankStatisticsRepository rankStatisticsRepository;

    @Autowired
    private GodDataRepository godDataRepository;

    public void configureMMRData(Integer tier, Float mmr) {
        rankStatisticsRepository.save(new RankStatistics(tier, mmr));
    }

    public void configureGodData(Float mmr, Integer godID, List<String> popularItems, List<String> popularActives, Integer winStatus,
                                 Integer damageDone, Integer basicAttackDamage, Integer damageMitigated) {
        GodData godData = godDataRepository.findById(godID).orElse(null);
        if(mmr < 1400.0) {

        } else if (mmr >= 1400.0 && mmr < 1800.0) {

        } else {

        }
    }

    public void configureMatchData(Float averageMMR, List<Integer> bannedGodIDs) {
        if(averageMMR < 1400.0) {
            for (Integer godID : bannedGodIDs) {
                GodData data = godDataRepository.findById(godID).get();
                data.setBansLowMMR(data.getBansLowMMR() + 1);
            }
        } else if (averageMMR >= 1400.0 && averageMMR < 1800.0) {
            for (Integer godID : bannedGodIDs) {
                GodData data = godDataRepository.findById(godID).orElse(null);
                data.setBansMediumMMR(data.getBansMediumMMR() + 1);
            }
        } else {
            for (Integer godID : bannedGodIDs) {
                GodData data = godDataRepository.findById(godID).orElse(null);
                data.setBansHighMMR(data.getBansHighMMR() + 1);
            }
        }

    }

}
