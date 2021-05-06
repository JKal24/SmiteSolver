package com.astro.SmiteSolver.entity.totaldata;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ban_rate_ranking")
public class BanRateRanking {

    @Id
    private Integer ranking;

    private Integer godID;

    private String godName;

    private double banRate;

    public BanRateRanking(Integer rank, Integer godID, String godName, double banRate) {
        this.ranking = rank;
        this.godID = godID;
        this.godName = godName;
        this.banRate = banRate;
    }

    public BanRateRanking() { }

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

    public double getBanRate() {
        return banRate;
    }

    public void setBanRate(double banRate) {
        this.banRate = banRate;
    }
}
