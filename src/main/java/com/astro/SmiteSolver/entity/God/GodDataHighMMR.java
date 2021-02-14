package com.astro.SmiteSolver.entity.God;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="god_data_high_mmr")
public class GodDataHighMMR {

    @Id
    private Integer dataID;

    private Integer godID;

    private Integer matchesPlayed;

    private Integer wins;

    private Integer bans;

    @ElementCollection
    private Map<String, Integer> skinsUsed;

    @ElementCollection
    private Map<String, Integer> popularItems;

    @ElementCollection
    private Map<String, Integer> popularActives;

    @ElementCollection
    private List<Integer> damageDone;

    @ElementCollection
    private List<Integer> basicAttackDamage;

    @ElementCollection
    private List<Integer> damageMitigated;

    public GodDataHighMMR(LocalDate date, Integer godID, Map<String, Integer> skinsUsed, Integer matchesPlayed, Integer wins,
                          Integer bans, Map<String, Integer> popularItems, Map<String, Integer> popularActives,
                         List<Integer> damageDone, List<Integer> basicAttackDamage, List<Integer> damageMitigated) {
        this.dataID = date.getDayOfMonth() + date.getMonthValue() + date.getYear() + godID;
        this.godID = godID;
        this.skinsUsed = skinsUsed;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.bans = bans;
        this.popularItems = popularItems;
        this.popularActives = popularActives;
        this.damageDone = damageDone;
        this.basicAttackDamage = basicAttackDamage;
        this.damageMitigated = damageMitigated;
    }

    public GodDataHighMMR() { }

    public Integer getDataID() {
        return dataID;
    }

    public Integer getGodID() {
        return godID;
    }

    public void setGodID(Integer godID) {
        this.godID = godID;
    }

    public Map<String, Integer> getSkinsUsed() {
        return skinsUsed;
    }

    public void setSkinsUsed(Map<String, Integer> skinsUsed) {
        this.skinsUsed = skinsUsed;
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

    public Map<String, Integer> getPopularItems() {
        return popularItems;
    }

    public void setPopularItems(Map<String, Integer> popularItems) {
        this.popularItems = popularItems;
    }

    public Map<String, Integer> getPopularActives() {
        return popularActives;
    }

    public void setPopularActives(Map<String, Integer> popularActives) {
        this.popularActives = popularActives;
    }

    public List<Integer> getDamageDone() {
        return damageDone;
    }

    public void setDamageDone(List<Integer> damageDone) {
        this.damageDone = damageDone;
    }

    public List<Integer> getBasicAttackDamage() {
        return basicAttackDamage;
    }

    public void setBasicAttackDamage(List<Integer> basicAttackDamage) {
        this.basicAttackDamage = basicAttackDamage;
    }

    public List<Integer> getDamageMitigated() {
        return damageMitigated;
    }

    public void setDamageMitigated(List<Integer> damageMitigated) {
        this.damageMitigated = damageMitigated;
    }
}
