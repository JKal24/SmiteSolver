package com.astro.SmiteSolver.entity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity(name="god_data_low_mmr")
public class DailyGodDataLowMMR extends DailyGodData {

    public DailyGodDataLowMMR(LocalDate date, Integer godID, String godName) {
        super(date, godID, godName, 0, 0, 0, new HashMap<>(), new HashMap<>(),
                new HashMap<>(), 0, 0, 0);
    }

    public DailyGodDataLowMMR(LocalDate date, Integer godID, String godName, Integer matchesPlayed, Integer wins,
                               Integer bans, Map<String, Integer> skinsUsed, Map<String, Integer> popularItems,
                               Map<String, Integer> popularActives, Integer averageDamageDone,
                               Integer averageBasicAttackDamage, Integer averageDamageMitigated) {
        super(date, godID, godName, matchesPlayed, wins, bans, skinsUsed, popularItems, popularActives,
                averageDamageDone, averageBasicAttackDamage, averageDamageMitigated);
    }

    public DailyGodDataLowMMR() { }
}
