package com.astro.SmiteSolver.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Map;

@Entity
public class GodData {

    @Id
    private Integer godID;

    private String godName;

    private Integer totalMatchesPlayedHighMMR;

    private Integer totalMatchesPlayedLowMMR;

    private BigDecimal movingPickRate;

    private BigDecimal newPatchPickRate;

    private Integer totalWinsHighMMR;

    private Integer totalWinsLowMMR;

    private BigDecimal movingWinRate;

    private BigDecimal newPatchWinRate;

    private Integer totalBansHighMMR;

    private Integer totalBansLowMMR;

    private BigDecimal movingBanRate;

    private BigDecimal newPatchBanRate;

    @ElementCollection
    private Map<String, Integer> skinsUsedHighMMR;

    @ElementCollection
    private Map<String, Integer> skinsUsedLowMMR;

    @ElementCollection
    private Map<String, Integer> popularItemsHighMMR;

    @ElementCollection
    private Map<String, Integer> popularItemsLowMMR;

    @ElementCollection
    private Map<String, Integer> newPatchPopularItemsHighMMR;

    @ElementCollection
    private Map<String, Integer> newPatchPopularItemsLowMMR;

    @ElementCollection
    private Map<String, Integer> popularActivesHighMMR;

    @ElementCollection
    private Map<String, Integer> popularActivesLowMMR;

    private Integer averageDamageDoneHighMMR;

    private Integer averageDamageDoneLowMMR;

    private Integer averageBasicAttackDamageHighMMR;

    private Integer averageBasicAttackDamageLowMMR;

    private Integer averageDamageMitigatedHighMMR;

    private Integer averageDamageMitigatedLowMMR;

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

    public Integer getTotalMatchesPlayedHighMMR() {
        return totalMatchesPlayedHighMMR;
    }

    public void setTotalMatchesPlayedHighMMR(Integer totalMatchesPlayedHighMMR) {
        this.totalMatchesPlayedHighMMR = totalMatchesPlayedHighMMR;
    }

    public Integer getTotalMatchesPlayedLowMMR() {
        return totalMatchesPlayedLowMMR;
    }

    public void setTotalMatchesPlayedLowMMR(Integer totalMatchesPlayedLowMMR) {
        this.totalMatchesPlayedLowMMR = totalMatchesPlayedLowMMR;
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

    public Integer getTotalWinsHighMMR() {
        return totalWinsHighMMR;
    }

    public void setTotalWinsHighMMR(Integer totalWinsHighMMR) {
        this.totalWinsHighMMR = totalWinsHighMMR;
    }

    public Integer getTotalWinsLowMMR() {
        return totalWinsLowMMR;
    }

    public void setTotalWinsLowMMR(Integer totalWinsLowMMR) {
        this.totalWinsLowMMR = totalWinsLowMMR;
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

    public Integer getTotalBansHighMMR() {
        return totalBansHighMMR;
    }

    public void setTotalBansHighMMR(Integer totalBansHighMMR) {
        this.totalBansHighMMR = totalBansHighMMR;
    }

    public Integer getTotalBansLowMMR() {
        return totalBansLowMMR;
    }

    public void setTotalBansLowMMR(Integer totalBansLowMMR) {
        this.totalBansLowMMR = totalBansLowMMR;
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

    public Map<String, Integer> getSkinsUsedHighMMR() {
        return skinsUsedHighMMR;
    }

    public void setSkinsUsedHighMMR(Map<String, Integer> skinsUsedHighMMR) {
        this.skinsUsedHighMMR = skinsUsedHighMMR;
    }

    public Map<String, Integer> getSkinsUsedLowMMR() {
        return skinsUsedLowMMR;
    }

    public void setSkinsUsedLowMMR(Map<String, Integer> skinsUsedLowMMR) {
        this.skinsUsedLowMMR = skinsUsedLowMMR;
    }

    public Map<String, Integer> getPopularItemsHighMMR() {
        return popularItemsHighMMR;
    }

    public void setPopularItemsHighMMR(Map<String, Integer> popularItemsHighMMR) {
        this.popularItemsHighMMR = popularItemsHighMMR;
    }

    public Map<String, Integer> getPopularItemsLowMMR() {
        return popularItemsLowMMR;
    }

    public void setPopularItemsLowMMR(Map<String, Integer> popularItemsLowMMR) {
        this.popularItemsLowMMR = popularItemsLowMMR;
    }

    public Map<String, Integer> getNewPatchPopularItemsHighMMR() {
        return newPatchPopularItemsHighMMR;
    }

    public void setNewPatchPopularItemsHighMMR(Map<String, Integer> newPatchPopularItemsHighMMR) {
        this.newPatchPopularItemsHighMMR = newPatchPopularItemsHighMMR;
    }

    public Map<String, Integer> getNewPatchPopularItemsLowMMR() {
        return newPatchPopularItemsLowMMR;
    }

    public void setNewPatchPopularItemsLowMMR(Map<String, Integer> newPatchPopularItemsLowMMR) {
        this.newPatchPopularItemsLowMMR = newPatchPopularItemsLowMMR;
    }

    public Map<String, Integer> getPopularActivesHighMMR() {
        return popularActivesHighMMR;
    }

    public void setPopularActivesHighMMR(Map<String, Integer> popularActivesHighMMR) {
        this.popularActivesHighMMR = popularActivesHighMMR;
    }

    public Map<String, Integer> getPopularActivesLowMMR() {
        return popularActivesLowMMR;
    }

    public void setPopularActivesLowMMR(Map<String, Integer> popularActivesLowMMR) {
        this.popularActivesLowMMR = popularActivesLowMMR;
    }

    public Integer getAverageDamageDoneHighMMR() {
        return averageDamageDoneHighMMR;
    }

    public void setAverageDamageDoneHighMMR(Integer averageDamageDoneHighMMR) {
        this.averageDamageDoneHighMMR = averageDamageDoneHighMMR;
    }

    public Integer getAverageDamageDoneLowMMR() {
        return averageDamageDoneLowMMR;
    }

    public void setAverageDamageDoneLowMMR(Integer averageDamageDoneLowMMR) {
        this.averageDamageDoneLowMMR = averageDamageDoneLowMMR;
    }

    public Integer getAverageBasicAttackDamageHighMMR() {
        return averageBasicAttackDamageHighMMR;
    }

    public void setAverageBasicAttackDamageHighMMR(Integer averageBasicAttackDamageHighMMR) {
        this.averageBasicAttackDamageHighMMR = averageBasicAttackDamageHighMMR;
    }

    public Integer getAverageBasicAttackDamageLowMMR() {
        return averageBasicAttackDamageLowMMR;
    }

    public void setAverageBasicAttackDamageLowMMR(Integer averageBasicAttackDamageLowMMR) {
        this.averageBasicAttackDamageLowMMR = averageBasicAttackDamageLowMMR;
    }

    public Integer getAverageDamageMitigatedHighMMR() {
        return averageDamageMitigatedHighMMR;
    }

    public void setAverageDamageMitigatedHighMMR(Integer averageDamageMitigatedHighMMR) {
        this.averageDamageMitigatedHighMMR = averageDamageMitigatedHighMMR;
    }

    public Integer getAverageDamageMitigatedLowMMR() {
        return averageDamageMitigatedLowMMR;
    }

    public void setAverageDamageMitigatedLowMMR(Integer averageDamageMitigatedLowMMR) {
        this.averageDamageMitigatedLowMMR = averageDamageMitigatedLowMMR;
    }
}
