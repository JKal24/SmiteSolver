package com.astro.SmiteSolver.entity.totaldata;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pick_rate_ranking")
public class PickRateRanking {

    @Id
    private Integer ranking;

    private Integer godID;

    private String godName;

    private double pickRate;

    public PickRateRanking(Integer rank, Integer godID, String godName, double pickRate) {
        this.ranking = rank;
        this.godID = godID;
        this.godName = godName;
        this.pickRate = pickRate;
    }

    public PickRateRanking() { }

    public Integer getRank() {
        return ranking;
    }

    public void setRank(Integer rank) {
        this.ranking = rank;
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

    public double getPickRate() {
        return pickRate;
    }

    public void setPickRate(double pickRate) {
        this.pickRate = pickRate;
    }
}
