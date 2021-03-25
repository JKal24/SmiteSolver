package com.astro.SmiteSolver.entity;

import javax.persistence.*;
import java.util.Map;

@MappedSuperclass
public class TotalGodData {

    @Id
    private Integer godID;

    private String godName;

    private Integer totalMatchesPlayed;

    private Integer newPatchMatchesPlayed;

    private double movingPickRate;

    private double newPatchPickRate;

    private Integer totalWins;

    private Integer newPatchWins;

    private double movingWinRate;

    private double newPatchWinRate;

    private Integer totalBans;

    private Integer newPatchBans;

    private double movingBanRate;

    private double newPatchBanRate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "total_data_skins_used",
            joinColumns = {@JoinColumn(name = "god_id", referencedColumnName = "godID")})
    @MapKeyColumn(name = "name")
    @Column(name = "count")
    private Map<String, Integer> skinsUsed;

    @ElementCollection(fetch = FetchType.LAZY, targetClass = Item.class)
    @CollectionTable(name = "total_data_items_used",
            joinColumns = {@JoinColumn(name = "god_id")})
    @MapKeyColumn(name = "name")
    private Map<Item, Integer> popularItems;

    @ElementCollection(fetch = FetchType.LAZY, targetClass = Item.class)
    @CollectionTable(name = "total_data_new_patch_items_used",
            joinColumns = {@JoinColumn(name = "god_id")})
    @MapKeyColumn(name = "name")
    @Column(name = "count")
    private Map<Item, Integer> newPatchPopularItems;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "total_data_actives_used",
            joinColumns = {@JoinColumn(name = "god_id", referencedColumnName = "godID")})
    @MapKeyColumn(name = "name")
    @Column(name = "count")
    private Map<String, Integer> popularActives;

    private Integer averageDamageDone;

    private Integer averageBasicAttackDamage;

    private Integer averageDamageMitigated;

    public TotalGodData(Integer godID, String godName, Integer totalMatchesPlayed, Integer newPatchMatchesPlayed,
                        double movingPickRate, double newPatchPickRate, Integer totalWins, Integer newPatchWins,
                        double movingWinRate, double newPatchWinRate, Integer totalBans, Integer newPatchBans,
                        double movingBanRate, double newPatchBanRate, Map<String, Integer> skinsUsed,
                        Map<Item, Integer> popularItems, Map<Item, Integer> newPatchPopularItems, Map<String, Integer> popularActives,
                        Integer averageDamageDone, Integer averageBasicAttackDamage, Integer averageDamageMitigated) {
        this.godID = godID;
        this.godName = godName;
        this.totalMatchesPlayed = totalMatchesPlayed;
        this.newPatchMatchesPlayed = newPatchMatchesPlayed;
        this.movingPickRate = movingPickRate;
        this.newPatchPickRate = newPatchPickRate;
        this.totalWins = totalWins;
        this.newPatchWins = newPatchWins;
        this.movingWinRate = movingWinRate;
        this.newPatchWinRate = newPatchWinRate;
        this.totalBans = totalBans;
        this.newPatchBans = newPatchBans;
        this.movingBanRate = movingBanRate;
        this.newPatchBanRate = newPatchBanRate;
        this.skinsUsed = skinsUsed;
        this.popularItems = popularItems;
        this.newPatchPopularItems = newPatchPopularItems;
        this.popularActives = popularActives;
        this.averageDamageDone = averageDamageDone;
        this.averageBasicAttackDamage = averageBasicAttackDamage;
        this.averageDamageMitigated = averageDamageMitigated;
    }

    public TotalGodData() { }

    public Integer getGodID() {
        return godID;
    }

    public String getGodName() {
        return godName;
    }

    public Integer getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }

    public void setTotalMatchesPlayed(Integer totalMatchesPlayed) {
        this.totalMatchesPlayed = totalMatchesPlayed;
    }

    public Integer getNewPatchMatchesPlayed() {
        return newPatchMatchesPlayed;
    }

    public void setNewPatchMatchesPlayed(Integer newPatchMatchesPlayed) {
        this.newPatchMatchesPlayed = newPatchMatchesPlayed;
    }

    public double getMovingPickRate() {
        return movingPickRate;
    }

    public void setMovingPickRate(double movingPickRate) {
        this.movingPickRate = movingPickRate;
    }

    public double getNewPatchPickRate() {
        return newPatchPickRate;
    }

    public void setNewPatchPickRate(double newPatchPickRate) {
        this.newPatchPickRate = newPatchPickRate;
    }

    public Integer getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(Integer totalWins) {
        this.totalWins = totalWins;
    }

    public Integer getNewPatchWins() {
        return newPatchWins;
    }

    public void setNewPatchWins(Integer newPatchWins) {
        this.newPatchWins = newPatchWins;
    }

    public double getMovingWinRate() {
        return movingWinRate;
    }

    public void setMovingWinRate(double movingWinRate) {
        this.movingWinRate = movingWinRate;
    }

    public double getNewPatchWinRate() {
        return newPatchWinRate;
    }

    public void setNewPatchWinRate(double newPatchWinRate) {
        this.newPatchWinRate = newPatchWinRate;
    }

    public Integer getTotalBans() {
        return totalBans;
    }

    public void setTotalBans(Integer totalBans) {
        this.totalBans = totalBans;
    }

    public Integer getNewPatchBans() {
        return newPatchBans;
    }

    public void setNewPatchBans(Integer newPatchBans) {
        this.newPatchBans = newPatchBans;
    }

    public double getMovingBanRate() {
        return movingBanRate;
    }

    public void setMovingBanRate(double movingBanRate) {
        this.movingBanRate = movingBanRate;
    }

    public double getNewPatchBanRate() {
        return newPatchBanRate;
    }

    public void setNewPatchBanRate(double newPatchBanRate) {
        this.newPatchBanRate = newPatchBanRate;
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

    public Integer getAverageDamageDone() {
        return averageDamageDone;
    }

    public void setAverageDamageDone(Integer averageDamageDone) {
        this.averageDamageDone = averageDamageDone;
    }

    public Integer getAverageBasicAttackDamage() {
        return averageBasicAttackDamage;
    }

    public void setAverageBasicAttackDamage(Integer averageBasicAttackDamage) {
        this.averageBasicAttackDamage = averageBasicAttackDamage;
    }

    public Integer getAverageDamageMitigated() {
        return averageDamageMitigated;
    }

    public void setAverageDamageMitigated(Integer averageDamageMitigated) {
        this.averageDamageMitigated = averageDamageMitigated;
    }

    @Override
    public String toString() {
        return "TotalGodData{" +
                "godID=" + godID +
                ", godName='" + godName + '\'' +
                ", totalMatchesPlayed=" + totalMatchesPlayed +
                ", newPatchMatchesPlayed=" + newPatchMatchesPlayed +
                ", movingPickRate=" + movingPickRate +
                ", newPatchPickRate=" + newPatchPickRate +
                ", totalWins=" + totalWins +
                ", newPatchWins=" + newPatchWins +
                ", movingWinRate=" + movingWinRate +
                ", newPatchWinRate=" + newPatchWinRate +
                ", totalBans=" + totalBans +
                ", newPatchBans=" + newPatchBans +
                ", movingBanRate=" + movingBanRate +
                ", newPatchBanRate=" + newPatchBanRate +
                ", skinsUsed=" + skinsUsed +
                ", popularItems=" + popularItems +
                ", newPatchPopularItems=" + newPatchPopularItems +
                ", popularActives=" + popularActives +
                ", averageDamageDone=" + averageDamageDone +
                ", averageBasicAttackDamage=" + averageBasicAttackDamage +
                ", averageDamageMitigated=" + averageDamageMitigated +
                '}';
    }
}
