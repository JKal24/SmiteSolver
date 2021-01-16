package com.astro.SmiteSolver.data;

import com.astro.SmiteSolver.object.GodData;

import java.util.Map;

public class GodDataService {

    public GodData configureMMR(Integer mmr, Integer matches, Integer wins, Integer losses, Integer bans,
                                Map<String, Integer> popularItems, Map<String, Integer> popularActives,
                                Integer averageDamageDone, Integer averageBasicAttackDamage, Integer averageDamageMitigated) {
        if(mmr < 1400) {

        } else if (mmr >= 1400 && mmr < 1800) {

        } else {

        }
    }

}
