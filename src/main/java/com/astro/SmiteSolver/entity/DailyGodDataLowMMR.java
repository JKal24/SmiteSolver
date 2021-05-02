package com.astro.SmiteSolver.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity(name="daily_god_data_low_mmr")
public class DailyGodDataLowMMR extends DailyGodData {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "daily_skins_low_mmr")
    private Map<String, Integer> skinsUsed;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "daily_items_low_mmr")
    private Map<Item, Integer> popularItems;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "daily_actives_low_mmr")
    private Map<String, Integer> popularActives;

    public DailyGodDataLowMMR(LocalDate date, Integer godID, String godName) {
        super(date, godID, godName, 0, 0, 0, 0, 0, 0);
        this.popularActives = new HashMap<>();
        this.popularItems = new HashMap<>();
        this.skinsUsed = new HashMap<>();
    }

    public DailyGodDataLowMMR(LocalDate date, Integer godID, String godName, Integer matchesPlayed, Integer wins,
                               Integer bans, Map<String, Integer> skinsUsed, Map<Item, Integer> popularItems,
                               Map<String, Integer> popularActives, Integer averageDamageDone,
                               Integer averageBasicAttackDamage, Integer averageDamageMitigated) {
        super(date, godID, godName, matchesPlayed, wins, bans, averageDamageDone, averageBasicAttackDamage, averageDamageMitigated);
        this.popularActives = popularActives;
        this.popularItems = popularItems;
        this.skinsUsed = skinsUsed;
    }

    public DailyGodDataLowMMR() { }

    public Map<String, Integer> getSkinsUsed() {
        return skinsUsed;
    }

    public void setSkinsUsed(Map<String, Integer> skinsUsed) {
        this.skinsUsed = skinsUsed;
    }

    public Map<Item, Integer> getPopularItems() {
        return popularItems;
    }

    public void setPopularItems(Map<Item, Integer> popularItems) {
        this.popularItems = popularItems;
    }

    public Map<String, Integer> getPopularActives() {
        return popularActives;
    }

    public void setPopularActives(Map<String, Integer> popularActives) {
        this.popularActives = popularActives;
    }
}
