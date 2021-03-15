package com.astro.SmiteSolver.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public class DailyGodData {

    @Id
    private Integer dataID;

    private LocalDate date;

    private Integer godID;

    private String godName;

    private Integer matchesPlayed;

    private Integer wins;

    private Integer bans;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "skins",
            joinColumns = @JoinColumn(name = "skins_id"))
    @MapKeyJoinColumn(name = "dataID")
    @Column(name = "skins_count")
    private Map<String, Integer> skinsUsed;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "items",
            joinColumns = @JoinColumn(name = "items_id"))
    @MapKeyJoinColumn(name = "dataID")
    @Column(name = "items_count")
    private Map<String, Integer> popularItems;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "actives",
            joinColumns = @JoinColumn(name = "actives_id"))
    @MapKeyJoinColumn(name = "dataID")
    @Column(name = "actives_count")
    private Map<String, Integer> popularActives;

    private Integer averageDamageDone;

    private Integer averageBasicAttackDamage;

    private Integer averageDamageMitigated;

    public DailyGodData(LocalDate date, Integer godID, String godName, Integer matchesPlayed, Integer wins, Integer bans,
                        Map<String, Integer> skinsUsed, Map<String, Integer> popularItems, Map<String, Integer> popularActives,
                        Integer averageDamageDone, Integer averageBasicAttackDamage, Integer averageDamageMitigated) {
        this.dataID = date.getDayOfMonth() + date.getMonthValue() + date.getYear() + godID;
        this.date = date;
        this.godID = godID;
        this.godName = godName;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.bans = bans;
        this.skinsUsed = skinsUsed;
        this.popularItems = popularItems;
        this.popularActives = popularActives;
        this.averageDamageDone = averageDamageDone;
        this.averageBasicAttackDamage = averageBasicAttackDamage;
        this.averageDamageMitigated = averageDamageMitigated;
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

    public Map<String, Integer> getSkinsUsed() {
        return new HashMap<>(skinsUsed);
    }

    public void setSkinsUsed(Map<String, Integer> skinsUsed) {
        this.skinsUsed = new HashMap<>(skinsUsed);
    }

    public Map<String, Integer> getPopularItems() {
        return new HashMap<>(popularItems);
    }

    public void setPopularItems(Map<String, Integer> popularItems) {
        this.popularItems = new HashMap<>(popularItems);
    }

    public Map<String, Integer> getPopularActives() {
        return new HashMap<>(popularActives);
    }

    public void setPopularActives(Map<String, Integer> popularActives) {
        this.popularActives = new HashMap<>(popularActives);
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
