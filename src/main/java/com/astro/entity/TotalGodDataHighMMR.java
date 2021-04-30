package com.astro.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name="total_god_data_high_mmr")
public class TotalGodDataHighMMR extends TotalGodData{

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "total_skins_high_mmr")
    private Map<String, Integer> skinsUsed;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "total_items_high_mmr")
    private Map<Item, Integer> popularItems;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "total_new_patch_items_high_mmr")
    private Map<Item, Integer> newPatchPopularItems;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "total_actives_high_mmr")
    private Map<String, Integer> popularActives;

    public TotalGodDataHighMMR(Integer godID, String godName) {
        super(godID, godName, 0,0, 0.0, 0.0, 0,
                0, 0.0, 0.0, 0, 0, 0.0,
                0.0, 0, 0, 0);
        this.skinsUsed = new HashMap<>();
        this.popularItems = new HashMap<>();
        this.newPatchPopularItems = new HashMap<>();
        this.popularActives = new HashMap<>();
    }

    public TotalGodDataHighMMR(Integer godID, String godName, Integer totalMatchesPlayed, Integer newPatchMatchesPlayed,
                               double movingPickRate, double newPatchPickRate, Integer totalWins, Integer newPatchWins,
                               double movingWinRate, double newPatchWinRate, Integer totalBans, Integer newPatchBans,
                               double movingBanRate, double newPatchBanRate, Map<String, Integer> skinsUsed,
                               Map<Item, Integer> popularItems, Map<Item, Integer> newPatchPopularItems,
                               Map<String, Integer> popularActives, Integer averageDamageDone, Integer averageBasicAttackDamage,
                               Integer averageDamageMitigated) {
        super(godID, godName, totalMatchesPlayed, newPatchMatchesPlayed, movingPickRate, newPatchPickRate, totalWins,
                newPatchWins, movingWinRate, newPatchWinRate, totalBans, newPatchBans, movingBanRate, newPatchBanRate,
                averageDamageDone, averageBasicAttackDamage, averageDamageMitigated);
        this.skinsUsed = skinsUsed;
        this.popularItems = popularItems;
        this.newPatchPopularItems = newPatchPopularItems;
        this.popularActives = popularActives;
    }

    public TotalGodDataHighMMR() {

    }

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

    public Map<Item, Integer> getNewPatchPopularItems() {
        return newPatchPopularItems;
    }

    public void setNewPatchPopularItems(Map<Item, Integer> newPatchPopularItems) {
        this.newPatchPopularItems = newPatchPopularItems;
    }

    public Map<String, Integer> getPopularActives() {
        return popularActives;
    }

    public void setPopularActives(Map<String, Integer> popularActives) {
        this.popularActives = popularActives;
    }
}
