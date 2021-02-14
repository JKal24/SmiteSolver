package com.astro.SmiteSolver.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "god_statistics_low_mmr")
public class PerformanceDataLowMMR {

    @Id
    private Integer godID;

    private String godName;

    private BigDecimal winRate;

    private BigDecimal newPatchWinRate;

    private BigDecimal pickRate;

    private BigDecimal newPatchPickRate;

    private BigDecimal banRate;

    private BigDecimal newPatchBanRate;

    @ElementCollection
    private Map<String, Integer> popularSkins;

    @ElementCollection
    private List<String> recommendedItems;

    @ElementCollection
    private List<String> newPatchRecommendedItems;

    @ElementCollection
    private Map<String, Integer> popularActives;

    @ElementCollection
    private Map<String, Integer> newPatchPopularActives;

    private Integer averageDamageDone;

    private Integer averageBasicAttackDamage;

    private Integer averageDamageMitigatedHighMMR;

    public PerformanceDataLowMMR() { }

    public PerformanceDataLowMMR(Integer godID, String godName, BigDecimal winRate, BigDecimal newPatchWinRate, BigDecimal pickRate,
                                 BigDecimal newPatchPickRate, BigDecimal banRate, BigDecimal newPatchBanRate, Map<String, Integer> popularSkins,
                                 List<String> recommendedItems, List<String> newPatchRecommendedItems, Map<String, Integer> popularActives,
                                 Map<String, Integer> newPatchPopularActives, Integer averageDamageDone, Integer averageBasicAttackDamage,
                                 Integer averageDamageMitigatedHighMMR) {
        this.godID = godID;
        this.godName = godName;
        this.winRate = winRate;
        this.newPatchWinRate = newPatchWinRate;
        this.pickRate = pickRate;
        this.newPatchPickRate = newPatchPickRate;
        this.banRate = banRate;
        this.newPatchBanRate = newPatchBanRate;
        this.popularSkins = popularSkins;
        this.recommendedItems = recommendedItems;
        this.newPatchRecommendedItems = newPatchRecommendedItems;
        this.popularActives = popularActives;
        this.newPatchPopularActives = newPatchPopularActives;
        this.averageDamageDone = averageDamageDone;
        this.averageBasicAttackDamage = averageBasicAttackDamage;
        this.averageDamageMitigatedHighMMR = averageDamageMitigatedHighMMR;
    }

    public Integer getGodID() {
        return godID;
    }

    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public BigDecimal getWinRate() {
        return winRate;
    }

    public void setWinRate(BigDecimal winRate) {
        this.winRate = winRate;
    }

    public BigDecimal getNewPatchWinRate() {
        return newPatchWinRate;
    }

    public void setNewPatchWinRate(BigDecimal newPatchWinRate) {
        this.newPatchWinRate = newPatchWinRate;
    }

    public BigDecimal getPickRate() {
        return pickRate;
    }

    public void setPickRate(BigDecimal pickRate) {
        this.pickRate = pickRate;
    }

    public BigDecimal getNewPatchPickRate() {
        return newPatchPickRate;
    }

    public void setNewPatchPickRate(BigDecimal newPatchPickRate) {
        this.newPatchPickRate = newPatchPickRate;
    }

    public BigDecimal getBanRate() {
        return banRate;
    }

    public void setBanRate(BigDecimal banRate) {
        this.banRate = banRate;
    }

    public BigDecimal getNewPatchBanRate() {
        return newPatchBanRate;
    }

    public void setNewPatchBanRate(BigDecimal newPatchBanRate) {
        this.newPatchBanRate = newPatchBanRate;
    }

    public Map<String, Integer> getPopularSkins() {
        return popularSkins;
    }

    public void setPopularSkins(Map<String, Integer> popularSkins) {
        this.popularSkins = popularSkins;
    }

    public List<String> getRecommendedItems() {
        return recommendedItems;
    }

    public void setRecommendedItems(List<String> recommendedItems) {
        this.recommendedItems = recommendedItems;
    }

    public List<String> getNewPatchRecommendedItems() {
        return newPatchRecommendedItems;
    }

    public void setNewPatchRecommendedItems(List<String> newPatchRecommendedItems) {
        this.newPatchRecommendedItems = newPatchRecommendedItems;
    }

    public Map<String, Integer> getPopularActives() {
        return popularActives;
    }

    public void setPopularActives(Map<String, Integer> popularActives) {
        this.popularActives = popularActives;
    }

    public Map<String, Integer> getNewPatchPopularActives() {
        return newPatchPopularActives;
    }

    public void setNewPatchPopularActives(Map<String, Integer> newPatchPopularActives) {
        this.newPatchPopularActives = newPatchPopularActives;
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

    public Integer getAverageDamageMitigatedHighMMR() {
        return averageDamageMitigatedHighMMR;
    }

    public void setAverageDamageMitigatedHighMMR(Integer averageDamageMitigatedHighMMR) {
        this.averageDamageMitigatedHighMMR = averageDamageMitigatedHighMMR;
    }
}
