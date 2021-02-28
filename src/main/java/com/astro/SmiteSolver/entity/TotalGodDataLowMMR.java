package com.astro.SmiteSolver.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity(name="total_god_data_low_mmr")
public class TotalGodDataLowMMR extends TotalGodData {
    public TotalGodDataLowMMR(Integer godID, String godName) {
        super(godID, godName, 0, new BigDecimal(0), new BigDecimal(0), 0, new BigDecimal(0),
                new BigDecimal(0), 0, new BigDecimal(0), new BigDecimal(0), new HashMap<String, Integer>(),
                new HashMap<String, Integer>(), new HashMap<String, Integer>(), new HashMap<String, Integer>(), 0,
                0, 0);
    }

    public TotalGodDataLowMMR(Integer godID, String godName, Integer totalMatchesPlayed, BigDecimal movingPickRate,
                              BigDecimal newPatchPickRate, Integer totalWins, BigDecimal movingWinRate, BigDecimal newPatchWinRate,
                              Integer totalBans, BigDecimal movingBanRate, BigDecimal newPatchBanRate, Map<String, Integer> skinsUsed,
                              Map<String, Integer> popularItems, Map<String, Integer> newPatchPopularItems, Map<String, Integer> popularActives,
                              Integer averageDamageDone, Integer averageBasicAttackDamage, Integer averageDamageMitigated) {
        super(godID, godName, totalMatchesPlayed, movingPickRate, newPatchPickRate, totalWins, movingWinRate, newPatchWinRate,
                totalBans, movingBanRate, newPatchBanRate, skinsUsed, popularItems, newPatchPopularItems, popularActives,
                averageDamageDone, averageBasicAttackDamage, averageDamageMitigated);
    }
}
