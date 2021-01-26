package com.astro.SmiteSolver.object;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "god_statistics")
public class GodStatistics {

    @Id
    private Integer godID;

    private String godName;

    private BigDecimal winRate;

    private BigDecimal pickRate;

    private BigDecimal banRate;

    @ElementCollection
    private List<String> recommendedItems;

    public GodStatistics(Integer godID, String godName, BigDecimal winRate, BigDecimal pickRate, BigDecimal banRate, List<String> recommendedItems) {
        this.godID = godID;
        this.godName = godName;
        this.winRate = winRate;
        this.pickRate = pickRate;
        this.banRate = banRate;
        this.recommendedItems = recommendedItems;
    }

    public GodStatistics() { }

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

    public BigDecimal getWinRate() {
        return winRate;
    }

    public void setWinRate(BigDecimal winRate) {
        this.winRate = winRate;
    }

    public BigDecimal getPickRate() {
        return pickRate;
    }

    public void setPickRate(BigDecimal pickRate) {
        this.pickRate = pickRate;
    }

    public BigDecimal getBanRate() {
        return banRate;
    }

    public void setBanRate(BigDecimal banRate) {
        this.banRate = banRate;
    }

    public List<String> getRecommendedItems() {
        return recommendedItems;
    }

    public void setRecommendedItems(List<String> recommendedItems) {
        this.recommendedItems = recommendedItems;
    }
}
