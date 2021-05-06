package com.astro.SmiteSolver.entity.dailydata;

import com.astro.SmiteSolver.entity.auxillary.Item;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@MappedSuperclass
public abstract class DailyGodData {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer dataID;

    private LocalDate date;

    private Integer godID;

    private String godName;

    private Integer matchesPlayed;

    private Integer wins;

    private Integer bans;

    private Integer averageDPM;

    private Integer averageBasicAttackDPM;

    private Integer averageDmgMitigatedPerMin;

    public DailyGodData(LocalDate date, Integer godID, String godName, Integer matchesPlayed, Integer wins, Integer bans,
                        Integer averageDPM, Integer averageBasicAttackDPM, Integer averageDmgMitigatedPerMin) {
        this.date = date;
        this.godID = godID;
        this.godName = godName;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.bans = bans;
        this.averageDPM = averageDPM;
        this.averageBasicAttackDPM = averageBasicAttackDPM;
        this.averageDmgMitigatedPerMin = averageDmgMitigatedPerMin;
    }

    public DailyGodData() { }

    public Integer getDataID() {
        return dataID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getBans() {
        return bans;
    }

    public void setBans(Integer bans) {
        this.bans = bans;
    }

    public Integer getAverageDPM() {
        return averageDPM;
    }

    public void setAverageDPM(Integer averageDPM) {
        this.averageDPM = averageDPM;
    }

    public Integer getAverageBasicAttackDPM() {
        return averageBasicAttackDPM;
    }

    public void setAverageBasicAttackDPM(Integer averageBasicAttackDPM) {
        this.averageBasicAttackDPM = averageBasicAttackDPM;
    }

    public Integer getAverageDmgMitigatedPerMin() {
        return averageDmgMitigatedPerMin;
    }

    public void setAverageDmgMitigatedPerMin(Integer averageDmgMitigatedPerMin) {
        this.averageDmgMitigatedPerMin = averageDmgMitigatedPerMin;
    }

    // Must have these fields in sub-tables.

    public abstract Map<String, Integer> getSkinsUsed();

    public abstract void setSkinsUsed(Map<String, Integer> skinsUsed);

    public abstract Map<Item, Integer> getPopularItems();

    public abstract void setPopularItems(Map<Item, Integer> popularItems);

    public abstract Map<String, Integer> getPopularActives();

    public abstract void setPopularActives(Map<String, Integer> popularActives);
}
