package com.astro.SmiteSolver.entity.totaldata;

import com.astro.SmiteSolver.entity.auxillary.Item;

import javax.persistence.*;
import java.util.Map;

@MappedSuperclass
public abstract class TotalGodData {

    @Id
    @Column(name = "god_id")
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

    private Integer averageDamageDone;

    private Integer averageBasicAttackDamage;

    private Integer averageDamageMitigated;

    public TotalGodData(Integer godID, String godName, Integer totalMatchesPlayed, Integer newPatchMatchesPlayed,
                        double movingPickRate, double newPatchPickRate, Integer totalWins, Integer newPatchWins,
                        double movingWinRate, double newPatchWinRate, Integer totalBans, Integer newPatchBans,
                        double movingBanRate, double newPatchBanRate, Integer averageDamageDone,
                        Integer averageBasicAttackDamage, Integer averageDamageMitigated) {
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
        this.averageDamageDone = averageDamageDone;
        this.averageBasicAttackDamage = averageBasicAttackDamage;
        this.averageDamageMitigated = averageDamageMitigated;
    }

    public TotalGodData() {

    }

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

    public abstract Map<String, Integer> getSkinsUsed();

    public abstract void setSkinsUsed(Map<String, Integer> skinsUsed);

    public abstract Map<Item, Integer> getPopularItems();

    public abstract void setPopularItems(Map<Item, Integer> popularItems);

    public abstract Map<Item, Integer> getNewPatchPopularItems();

    public abstract void setNewPatchPopularItems(Map<Item, Integer> newPatchPopularItems);

    public abstract Map<String, Integer> getPopularActives();

    public abstract void setPopularActives(Map<String, Integer> popularActives);

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
                ", averageDamageDone=" + averageDamageDone +
                ", averageBasicAttackDamage=" + averageBasicAttackDamage +
                ", averageDamageMitigated=" + averageDamageMitigated +
                '}';
    }
}
