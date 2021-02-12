package com.astro.SmiteSolver.object.God;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="god_data")
public class GodData {

    @Id
    private Integer godID;
    private LocalDate date;

    @ElementCollection
    private Map<String, Integer> popularSkins;

    private Integer matchesHighMMR;
    private Integer matchesLowMMR;

    private Integer winsHighMMR;
    private Integer winsLowMMR;

    private Integer lossesHighMMR;
    private Integer lossesLowMMR;

    private Integer bansHighMMR;
    private Integer bansLowMMR;

    @ElementCollection
    private Map<String, Integer> popularItemsHighMMR;

    @ElementCollection
    private Map<String, Integer> popularItemsLowMMR;

    @ElementCollection
    private Map<String, Integer> popularActivesHighMMR;

    @ElementCollection
    private Map<String, Integer> popularActivesLowMMR;

    @ElementCollection
    private List<Integer> damageDoneHighMMR;

    @ElementCollection
    private List<Integer> damageDoneLowMMR;

    @ElementCollection
    private List<Integer> basicAttackDamageHighMMR;

    @ElementCollection
    private List<Integer> basicAttackDamageLowMMR;

    @ElementCollection
    private List<Integer> damageMitigatedHighMMR;

    @ElementCollection
    private List<Integer> damageMitigatedLowMMR;

    public GodData(LocalDate date, Map<String, Integer> popularSkins, Integer matchesHighMMR, Integer matchesLowMMR,
                   Integer winsHighMMR, Integer winsLowMMR, Integer lossesHighMMR, Integer lossesLowMMR, Integer bansHighMMR,
                   Integer bansLowMMR, Map<String, Integer> popularItemsHighMMR, Map<String, Integer> popularItemsLowMMR,
                   Map<String, Integer> popularActivesHighMMR, Map<String, Integer> popularActivesLowMMR,
                   List<Integer> damageDoneHighMMR, List<Integer> damageDoneLowMMR, List<Integer> basicAttackDamageHighMMR,
                   List<Integer> basicAttackDamageLowMMR, List<Integer> damageMitigatedHighMMR, List<Integer> damageMitigatedLowMMR) {
        this.date = date;
        this.popularSkins = popularSkins;
        this.matchesHighMMR = matchesHighMMR;
        this.matchesLowMMR = matchesLowMMR;
        this.winsHighMMR = winsHighMMR;
        this.winsLowMMR = winsLowMMR;
        this.lossesHighMMR = lossesHighMMR;
        this.lossesLowMMR = lossesLowMMR;
        this.bansHighMMR = bansHighMMR;
        this.bansLowMMR = bansLowMMR;
        this.popularItemsHighMMR = popularItemsHighMMR;
        this.popularItemsLowMMR = popularItemsLowMMR;
        this.popularActivesHighMMR = popularActivesHighMMR;
        this.popularActivesLowMMR = popularActivesLowMMR;
        this.damageDoneHighMMR = damageDoneHighMMR;
        this.damageDoneLowMMR = damageDoneLowMMR;
        this.basicAttackDamageHighMMR = basicAttackDamageHighMMR;
        this.basicAttackDamageLowMMR = basicAttackDamageLowMMR;
        this.damageMitigatedHighMMR = damageMitigatedHighMMR;
        this.damageMitigatedLowMMR = damageMitigatedLowMMR;
    }

    public GodData() { }

    public Integer getGodID() {
        return godID;
    }

    public void setGodID(Integer godID) {
        this.godID = godID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Integer> getPopularSkins() {
        return popularSkins;
    }

    public void setPopularSkins(Map<String, Integer> popularSkins) {
        this.popularSkins = popularSkins;
    }

    public Integer getMatchesHighMMR() {
        return matchesHighMMR;
    }

    public void setMatchesHighMMR(Integer matchesHighMMR) {
        this.matchesHighMMR = matchesHighMMR;
    }

    public Integer getMatchesLowMMR() {
        return matchesLowMMR;
    }

    public void setMatchesLowMMR(Integer matchesLowMMR) {
        this.matchesLowMMR = matchesLowMMR;
    }

    public Integer getWinsHighMMR() {
        return winsHighMMR;
    }

    public void setWinsHighMMR(Integer winsHighMMR) {
        this.winsHighMMR = winsHighMMR;
    }

    public Integer getWinsLowMMR() {
        return winsLowMMR;
    }

    public void setWinsLowMMR(Integer winsLowMMR) {
        this.winsLowMMR = winsLowMMR;
    }

    public Integer getLossesHighMMR() {
        return lossesHighMMR;
    }

    public void setLossesHighMMR(Integer lossesHighMMR) {
        this.lossesHighMMR = lossesHighMMR;
    }

    public Integer getLossesLowMMR() {
        return lossesLowMMR;
    }

    public void setLossesLowMMR(Integer lossesLowMMR) {
        this.lossesLowMMR = lossesLowMMR;
    }

    public Integer getBansHighMMR() {
        return bansHighMMR;
    }

    public void setBansHighMMR(Integer bansHighMMR) {
        this.bansHighMMR = bansHighMMR;
    }

    public Integer getBansLowMMR() {
        return bansLowMMR;
    }

    public void setBansLowMMR(Integer bansLowMMR) {
        this.bansLowMMR = bansLowMMR;
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

    public List<Integer> getDamageDoneHighMMR() {
        return damageDoneHighMMR;
    }

    public void setDamageDoneHighMMR(List<Integer> damageDoneHighMMR) {
        this.damageDoneHighMMR = damageDoneHighMMR;
    }

    public List<Integer> getDamageDoneLowMMR() {
        return damageDoneLowMMR;
    }

    public void setDamageDoneLowMMR(List<Integer> damageDoneLowMMR) {
        this.damageDoneLowMMR = damageDoneLowMMR;
    }

    public List<Integer> getBasicAttackDamageHighMMR() {
        return basicAttackDamageHighMMR;
    }

    public void setBasicAttackDamageHighMMR(List<Integer> basicAttackDamageHighMMR) {
        this.basicAttackDamageHighMMR = basicAttackDamageHighMMR;
    }

    public List<Integer> getBasicAttackDamageLowMMR() {
        return basicAttackDamageLowMMR;
    }

    public void setBasicAttackDamageLowMMR(List<Integer> basicAttackDamageLowMMR) {
        this.basicAttackDamageLowMMR = basicAttackDamageLowMMR;
    }

    public List<Integer> getDamageMitigatedHighMMR() {
        return damageMitigatedHighMMR;
    }

    public void setDamageMitigatedHighMMR(List<Integer> damageMitigatedHighMMR) {
        this.damageMitigatedHighMMR = damageMitigatedHighMMR;
    }

    public List<Integer> getDamageMitigatedLowMMR() {
        return damageMitigatedLowMMR;
    }

    public void setDamageMitigatedLowMMR(List<Integer> damageMitigatedLowMMR) {
        this.damageMitigatedLowMMR = damageMitigatedLowMMR;
    }
}
