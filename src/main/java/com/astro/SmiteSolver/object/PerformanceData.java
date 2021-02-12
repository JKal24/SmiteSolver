package com.astro.SmiteSolver.object;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "god_statistics")
public class PerformanceData {

    @Id
    private Integer godID;

    private String godName;

    private BigDecimal monthlyWinRate;

    private BigDecimal patchWinRate;

    private BigDecimal monthlyPickRate;

    private BigDecimal patchPickRate;

    private BigDecimal monthlyBanRate;

    private BigDecimal patchBanRate;

    @ElementCollection
//    @MapKeyColumn(name="items_high_mmr")
//    @Column(name="item_high_mrr_id")
//    @CollectionTable(name="popular_items_high_mmr", joinColumns=@JoinColumn(name="popular_items_high_mmr_id"))
    private List<String> monthlyRecommendedItems;

    @ElementCollection
//    @MapKeyColumn(name="patch_recommended_items")
//    @Column(name="patch_recommended_items_id")
//    @CollectionTable(name="god_patch_recommended_items", joinColumns=@JoinColumn(name="patch_recommended_items_id"))
    private List<String> patchRecommendedItems;

    public PerformanceData(String godName, BigDecimal monthlyWinRate, BigDecimal patchWinRate, BigDecimal monthlyPickRate,
                           BigDecimal patchPickRate, BigDecimal monthlyBanRate, BigDecimal patchBanRate, List<String> monthlyRecommendedItems) {
        this.godName = godName;
        this.monthlyWinRate = monthlyWinRate;
        this.patchWinRate = patchWinRate;
        this.monthlyPickRate = monthlyPickRate;
        this.patchPickRate = patchPickRate;
        this.monthlyBanRate = monthlyBanRate;
        this.patchBanRate = patchBanRate;
        this.monthlyRecommendedItems = monthlyRecommendedItems;
    }

    public PerformanceData() { }

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

    public BigDecimal getMonthlyWinRate() {
        return monthlyWinRate;
    }

    public void setMonthlyWinRate(BigDecimal monthlyWinRate) {
        this.monthlyWinRate = monthlyWinRate;
    }

    public BigDecimal getPatchWinRate() {
        return patchWinRate;
    }

    public void setPatchWinRate(BigDecimal patchWinRate) {
        this.patchWinRate = patchWinRate;
    }

    public BigDecimal getMonthlyPickRate() {
        return monthlyPickRate;
    }

    public void setMonthlyPickRate(BigDecimal monthlyPickRate) {
        this.monthlyPickRate = monthlyPickRate;
    }

    public BigDecimal getPatchPickRate() {
        return patchPickRate;
    }

    public void setPatchPickRate(BigDecimal patchPickRate) {
        this.patchPickRate = patchPickRate;
    }

    public BigDecimal getMonthlyBanRate() {
        return monthlyBanRate;
    }

    public void setMonthlyBanRate(BigDecimal monthlyBanRate) {
        this.monthlyBanRate = monthlyBanRate;
    }

    public BigDecimal getPatchBanRate() {
        return patchBanRate;
    }

    public void setPatchBanRate(BigDecimal patchBanRate) {
        this.patchBanRate = patchBanRate;
    }

    public List<String> getMonthlyRecommendedItems() {
        return monthlyRecommendedItems;
    }

    public void setMonthlyRecommendedItems(List<String> monthlyRecommendedItems) {
        this.monthlyRecommendedItems = monthlyRecommendedItems;
    }

    public List<String> getPatchRecommendedItems() {
        return patchRecommendedItems;
    }

    public void setPatchRecommendedItems(List<String> patchRecommendedItems) {
        this.patchRecommendedItems = patchRecommendedItems;
    }
}
