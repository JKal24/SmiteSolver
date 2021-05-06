package com.astro.SmiteSolver.entity.totaldata;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "win_rate_ranking")
public class WinRateRanking {

    @Id
    private Integer ranking;

    private Integer godID;

    private String godName;

    private double winRate;

    public WinRateRanking(Integer rank, Integer godID, String godName, double winRate) {
        this.ranking = rank;
        this.godID = godID;
        this.godName = godName;
        this.winRate = winRate;
    }

    public WinRateRanking() { }

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

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
}
