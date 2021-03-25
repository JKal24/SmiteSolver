package com.astro.SmiteSolver.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity(name="total_god_data_high_mmr")
public class TotalGodDataHighMMR extends TotalGodData{
    public TotalGodDataHighMMR(Integer godID, String godName) {
        super(godID, godName, 0,0, 0.0, 0.0, 0,
                0, 0.0, 0.0, 0, 0, 0.0,
                0.0, new HashMap<String, Integer>(), new HashMap<>(), new HashMap<>(),
                new HashMap<>(), 0, 0, 0);
    }

    public TotalGodDataHighMMR(Integer godID, String godName, Integer totalMatchesPlayed, Integer newPatchMatchesPlayed,
                               double movingPickRate, double newPatchPickRate, Integer totalWins, Integer newPatchWins,
                               double movingWinRate, double newPatchWinRate, Integer totalBans, Integer newPatchBans,
                               double movingBanRate, double newPatchBanRate, Map<String, Integer> skinsUsed,
                               Map<Item, Integer> popularItems, Map<Item, Integer> newPatchPopularItems,
                               Map<String, Integer> popularActives, Integer averageDamageDone, Integer averageBasicAttackDamage,
                               Integer averageDamageMitigated) {
        super(godID, godName, totalMatchesPlayed, newPatchMatchesPlayed, movingPickRate, newPatchPickRate, totalWins, newPatchWins,
                movingWinRate, newPatchWinRate, totalBans, newPatchBans, movingBanRate, newPatchBanRate, skinsUsed, popularItems,
                newPatchPopularItems, popularActives, averageDamageDone, averageBasicAttackDamage, averageDamageMitigated);
    }

    public TotalGodDataHighMMR() {
    }
}
