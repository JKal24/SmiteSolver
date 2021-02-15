package com.astro.SmiteSolver.entity.God;

import javax.persistence.ElementCollection;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MappedSuperclass
public class GodData {

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

    public GodData(LocalDate date, Integer godID, Integer matchesPlayed, Integer wins, Integer bans,
                   Map<String, Integer> skinsUsed, Map<String, Integer> popularItems, Map<String, Integer> popularActives,
                   List<Integer> damageDone, List<Integer> basicAttackDamage, List<Integer> damageMitigated) {
        this.dataID = date.getDayOfMonth() + date.getMonthValue() + date.getYear() + godID;
        this.godID = godID;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.bans = bans;
        this.skinsUsed = skinsUsed;
        this.popularItems = popularItems;
        this.popularActives = popularActives;
        this.damageDone = damageDone;
        this.basicAttackDamage = basicAttackDamage;
        this.damageMitigated = damageMitigated;
    }

    public Integer getDataID() {
        return dataID;
    }

    private void setDataID(Integer dataID) {
        this.dataID = dataID;
    }

    public Integer getGodID() {
        return godID;
    }

    public void setGodID(Integer godID) {
        this.godID = godID;
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

    public List<Integer> getDamageDone() {
        return new ArrayList<>(damageDone);
    }

    public void setDamageDone(List<Integer> damageDone) {
        this.damageDone = new ArrayList<>(damageDone);
    }

    public List<Integer> getBasicAttackDamage() {
        return new ArrayList<>(basicAttackDamage);
    }

    public void setBasicAttackDamage(List<Integer> basicAttackDamage) {
        this.basicAttackDamage = new ArrayList<>(basicAttackDamage);
    }

    public List<Integer> getDamageMitigated() {
        return new ArrayList<>(damageMitigated);
    }

    public void setDamageMitigated(List<Integer> damageMitigated) {
        this.damageMitigated = new ArrayList<>(damageMitigated);
    }
}
