package com.astro.SmiteSolver.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Map;

@MappedSuperclass
public class TotalGodData {

    @Id
    private Integer godID;

    private String godName;

    private Integer totalMatchesPlayed;

    private BigDecimal movingPickRate;

    private BigDecimal newPatchPickRate;

    private Integer totalWins;

    private BigDecimal movingWinRate;

    private BigDecimal newPatchWinRate;

    private Integer totalBans;

    private BigDecimal movingBanRate;

    private BigDecimal newPatchBanRate;

    @ElementCollection
    private Map<String, Integer> skinsUsed;

    @ElementCollection
    private Map<String, Integer> popularItems;

    @ElementCollection
    private Map<String, Integer> newPatchPopularItems;

    @ElementCollection
    private Map<String, Integer> popularActives;

    private Integer averageDamageDone;

    private Integer averageBasicAttackDamage;

    private Integer averageDamageMitigated;

    public TotalGodData(Integer godID, String godName, Integer totalMatchesPlayed, BigDecimal movingPickRate,
                        BigDecimal newPatchPickRate, Integer totalWins, BigDecimal movingWinRate, BigDecimal newPatchWinRate,
                        Integer totalBans, BigDecimal movingBanRate, BigDecimal newPatchBanRate, Map<String, Integer> skinsUsed,
                        Map<String, Integer> popularItems, Map<String, Integer> newPatchPopularItems, Map<String, Integer> popularActives,
                        Integer averageDamageDone, Integer averageBasicAttackDamage, Integer averageDamageMitigated) {
        this.godID = godID;
        this.godName = godName;
        this.totalMatchesPlayed = totalMatchesPlayed;
        this.movingPickRate = movingPickRate;
        this.newPatchPickRate = newPatchPickRate;
        this.totalWins = totalWins;
        this.movingWinRate = movingWinRate;
        this.newPatchWinRate = newPatchWinRate;
        this.totalBans = totalBans;
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

    public Integer getGodID() {
        return godID;
    }

    public void setGodID(Integer godID) {
        this.godID = godID;
    }

    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public Integer getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }

    public void setTotalMatchesPlayed(Integer totalMatchesPlayed) {
        this.totalMatchesPlayed = totalMatchesPlayed;
    }

    public BigDecimal getMovingPickRate() {
        return movingPickRate;
    }

    public void setMovingPickRate(BigDecimal movingPickRate) {
        this.movingPickRate = movingPickRate;
    }

    public BigDecimal getNewPatchPickRate() {
        return newPatchPickRate;
    }

    public void setNewPatchPickRate(BigDecimal newPatchPickRate) {
        this.newPatchPickRate = newPatchPickRate;
    }

    public Integer getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(Integer totalWins) {
        this.totalWins = totalWins;
    }

    public BigDecimal getMovingWinRate() {
        return movingWinRate;
    }

    public void setMovingWinRate(BigDecimal movingWinRate) {
        this.movingWinRate = movingWinRate;
    }

    public BigDecimal getNewPatchWinRate() {
        return newPatchWinRate;
    }

    public void setNewPatchWinRate(BigDecimal newPatchWinRate) {
        this.newPatchWinRate = newPatchWinRate;
    }

    public Integer getTotalBans() {
        return totalBans;
    }

    public void setTotalBans(Integer totalBans) {
        this.totalBans = totalBans;
    }

    public BigDecimal getMovingBanRate() {
        return movingBanRate;
    }

    public void setMovingBanRate(BigDecimal movingBanRate) {
        this.movingBanRate = movingBanRate;
    }

    public BigDecimal getNewPatchBanRate() {
        return newPatchBanRate;
    }

    public void setNewPatchBanRate(BigDecimal newPatchBanRate) {
        this.newPatchBanRate = newPatchBanRate;
    }

    public Map<String, Integer> getSkinsUsed() {
        return skinsUsed;
    }

    public void setSkinsUsed(Map<String, Integer> skinsUsed) {
        this.skinsUsed = skinsUsed;
    }

    public Map<String, Integer> getPopularItems() {
        return popularItems;
    }

    public void setPopularItems(Map<String, Integer> popularItems) {
        this.popularItems = popularItems;
    }

    public Map<String, Integer> getNewPatchPopularItems() {
        return newPatchPopularItems;
    }

    public void setNewPatchPopularItems(Map<String, Integer> newPatchPopularItems) {
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
}