package com.astro.SmiteSolver.object;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Entity
public class GodData {

    @Id
    private Integer godID;
    private LocalDate date;

    @ElementCollection
    private Map<String, Integer> popularSkins;

    private Integer matchesHighMMR;
    private Integer matchesMediumMMR;
    private Integer matchesLowMMR;

    private Integer winsHighMMR;
    private Integer winsMediumMMR;
    private Integer winsLowMMR;

    private Integer lossesHighMMR;
    private Integer lossesMediumMMR;
    private Integer lossesLowMMR;

    private Integer bansHighMMR;
    private Integer bansMediumMMR;
    private Integer bansLowMMR;

    @ElementCollection
    private Map<String, Integer> popularItemsHighMMR;
    @ElementCollection
    private Map<String, Integer> popularItemsMediumMMR;
    @ElementCollection
    private Map<String, Integer> popularItemsLowMMR;

    @ElementCollection
    private Map<String, Integer> popularActivesHighMMR;
    @ElementCollection
    private Map<String, Integer> popularActivesMediumMMR;
    @ElementCollection
    private Map<String, Integer> popularActivesLowMMR;

    private Integer damageDoneHighMMR;
    private Integer damageDoneMediumMMR;
    private Integer damageDoneLowMMR;

    private Integer basicAttackDamageHighMMR;
    private Integer basicAttackDamageMediumMMR;
    private Integer basicAttackDamageLowMMR;

    private Integer damageMitigatedHighMMR;
    private Integer damageMitigatedMediumMMR;
    private Integer damageMitigatedLowMMR;

    public GodData(LocalDate date, Integer godID, Map<String, Integer> popularSkins, Integer matchesHighMMR,
                   Integer matchesMediumMMR, Integer matchesLowMMR, Integer winsHighMMR, Integer winsMediumMMR, Integer winsLowMMR,
                   Integer lossesHighMMR, Integer lossesMediumMMR, Integer lossesLowMMR, Integer bansHighMMR, Integer bansMediumMMR,
                   Integer bansLowMMR, Map<String, Integer> popularItemsHighMMR, Map<String, Integer> popularItemsMediumMMR,
                   Map<String, Integer> popularItemsLowMMR, Map<String, Integer> popularActivesHighMMR, Map<String, Integer> popularActivesMediumMMR,
                   Map<String, Integer> popularActivesLowMMR, Integer damageDoneHighMMR, Integer damageDoneMediumMMR,
                   Integer damageDoneLowMMR, Integer basicAttackDamageHighMMR, Integer basicAttackDamageMediumMMR,
                   Integer basicAttackDamageLowMMR, Integer damageMitigatedHighMMR, Integer damageMitigatedMediumMMR, Integer damageMitigatedLowMMR) {
        this.date = date;
        this.godID = godID;
        this.popularSkins = popularSkins;
        this.matchesHighMMR = matchesHighMMR;
        this.matchesMediumMMR = matchesMediumMMR;
        this.matchesLowMMR = matchesLowMMR;
        this.winsHighMMR = winsHighMMR;
        this.winsMediumMMR = winsMediumMMR;
        this.winsLowMMR = winsLowMMR;
        this.lossesHighMMR = lossesHighMMR;
        this.lossesMediumMMR = lossesMediumMMR;
        this.lossesLowMMR = lossesLowMMR;
        this.bansHighMMR = bansHighMMR;
        this.bansMediumMMR = bansMediumMMR;
        this.bansLowMMR = bansLowMMR;
        this.popularItemsHighMMR = popularItemsHighMMR;
        this.popularItemsMediumMMR = popularItemsMediumMMR;
        this.popularItemsLowMMR = popularItemsLowMMR;
        this.popularActivesHighMMR = popularActivesHighMMR;
        this.popularActivesMediumMMR = popularActivesMediumMMR;
        this.popularActivesLowMMR = popularActivesLowMMR;
        this.damageDoneHighMMR = damageDoneHighMMR;
        this.damageDoneMediumMMR = damageDoneMediumMMR;
        this.damageDoneLowMMR = damageDoneLowMMR;
        this.basicAttackDamageHighMMR = basicAttackDamageHighMMR;
        this.basicAttackDamageMediumMMR = basicAttackDamageMediumMMR;
        this.basicAttackDamageLowMMR = basicAttackDamageLowMMR;
        this.damageMitigatedHighMMR = damageMitigatedHighMMR;
        this.damageMitigatedMediumMMR = damageMitigatedMediumMMR;
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

    public Integer getMatchesMediumMMR() {
        return matchesMediumMMR;
    }

    public void setMatchesMediumMMR(Integer matchesMediumMMR) {
        this.matchesMediumMMR = matchesMediumMMR;
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

    public Integer getWinsMediumMMR() {
        return winsMediumMMR;
    }

    public void setWinsMediumMMR(Integer winsMediumMMR) {
        this.winsMediumMMR = winsMediumMMR;
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

    public Integer getLossesMediumMMR() {
        return lossesMediumMMR;
    }

    public void setLossesMediumMMR(Integer lossesMediumMMR) {
        this.lossesMediumMMR = lossesMediumMMR;
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

    public Integer getBansMediumMMR() {
        return bansMediumMMR;
    }

    public void setBansMediumMMR(Integer bansMediumMMR) {
        this.bansMediumMMR = bansMediumMMR;
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

    public Map<String, Integer> getPopularItemsMediumMMR() {
        return popularItemsMediumMMR;
    }

    public void setPopularItemsMediumMMR(Map<String, Integer> popularItemsMediumMMR) {
        this.popularItemsMediumMMR = popularItemsMediumMMR;
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

    public Map<String, Integer> getPopularActivesMediumMMR() {
        return popularActivesMediumMMR;
    }

    public void setPopularActivesMediumMMR(Map<String, Integer> popularActivesMediumMMR) {
        this.popularActivesMediumMMR = popularActivesMediumMMR;
    }

    public Map<String, Integer> getPopularActivesLowMMR() {
        return popularActivesLowMMR;
    }

    public void setPopularActivesLowMMR(Map<String, Integer> popularActivesLowMMR) {
        this.popularActivesLowMMR = popularActivesLowMMR;
    }

    public Integer getDamageDoneHighMMR() {
        return damageDoneHighMMR;
    }

    public void setDamageDoneHighMMR(Integer damageDoneHighMMR) {
        this.damageDoneHighMMR = damageDoneHighMMR;
    }

    public Integer getDamageDoneMediumMMR() {
        return damageDoneMediumMMR;
    }

    public void setDamageDoneMediumMMR(Integer damageDoneMediumMMR) {
        this.damageDoneMediumMMR = damageDoneMediumMMR;
    }

    public Integer getDamageDoneLowMMR() {
        return damageDoneLowMMR;
    }

    public void setDamageDoneLowMMR(Integer damageDoneLowMMR) {
        this.damageDoneLowMMR = damageDoneLowMMR;
    }

    public Integer getBasicAttackDamageHighMMR() {
        return basicAttackDamageHighMMR;
    }

    public void setBasicAttackDamageHighMMR(Integer basicAttackDamageHighMMR) {
        this.basicAttackDamageHighMMR = basicAttackDamageHighMMR;
    }

    public Integer getBasicAttackDamageMediumMMR() {
        return basicAttackDamageMediumMMR;
    }

    public void setBasicAttackDamageMediumMMR(Integer basicAttackDamageMediumMMR) {
        this.basicAttackDamageMediumMMR = basicAttackDamageMediumMMR;
    }

    public Integer getBasicAttackDamageLowMMR() {
        return basicAttackDamageLowMMR;
    }

    public void setBasicAttackDamageLowMMR(Integer basicAttackDamageLowMMR) {
        this.basicAttackDamageLowMMR = basicAttackDamageLowMMR;
    }

    public Integer getDamageMitigatedHighMMR() {
        return damageMitigatedHighMMR;
    }

    public void setDamageMitigatedHighMMR(Integer damageMitigatedHighMMR) {
        this.damageMitigatedHighMMR = damageMitigatedHighMMR;
    }

    public Integer getDamageMitigatedMediumMMR() {
        return damageMitigatedMediumMMR;
    }

    public void setDamageMitigatedMediumMMR(Integer damageMitigatedMediumMMR) {
        this.damageMitigatedMediumMMR = damageMitigatedMediumMMR;
    }

    public Integer getDamageMitigatedLowMMR() {
        return damageMitigatedLowMMR;
    }

    public void setDamageMitigatedLowMMR(Integer damageMitigatedLowMMR) {
        this.damageMitigatedLowMMR = damageMitigatedLowMMR;
    }
}
