package com.astro.SmiteSolver.object;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Table()
public class GodData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private LocalDate date;

    private Integer godID;

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

    private Map<String, Integer> popularItemsHighMMR;
    private Map<String, Integer> popularItemsMediumMMR;
    private Map<String, Integer> popularItemsLowMMR;

    private Map<String, Integer> popularActivesHighMMR;
    private Map<String, Integer> popularActivesMediumMMR;
    private Map<String, Integer> popularActivesLowMMR;

    private Integer meanDamageDoneHighMMR;
    private Integer meanDamageDoneMediumMMR;
    private Integer meanDamageDoneLowMMR;

    private Integer meanBasicAttackDamageHighMMR;
    private Integer meanBasicAttackDamageMediumMMR;
    private Integer meanBasicAttackDamageLowMMR;

    private Integer meanDamageMitigatedHighMMR;
    private Integer meanDamageMitigatedMediumMMR;
    private Integer meanDamageMitigatedLowMMR;

    public GodData(LocalDate date, Integer godID, Map<String, Integer> popularSkins, Integer matchesHighMMR,
                   Integer matchesMediumMMR, Integer matchesLowMMR, Integer winsHighMMR, Integer winsMediumMMR, Integer winsLowMMR,
                   Integer lossesHighMMR, Integer lossesMediumMMR, Integer lossesLowMMR, Integer bansHighMMR, Integer bansMediumMMR,
                   Integer bansLowMMR, Map<String, Integer> popularItemsHighMMR, Map<String, Integer> popularItemsMediumMMR,
                   Map<String, Integer> popularItemsLowMMR, Map<String, Integer> popularActivesHighMMR, Map<String, Integer> popularActivesMediumMMR,
                   Map<String, Integer> popularActivesLowMMR, Integer meanDamageDoneHighMMR, Integer meanDamageDoneMediumMMR,
                   Integer meanDamageDoneLowMMR, Integer meanBasicAttackDamageHighMMR, Integer meanBasicAttackDamageMediumMMR,
                   Integer meanBasicAttackDamageLowMMR, Integer meanDamageMitigatedHighMMR, Integer meanDamageMitigatedMediumMMR, Integer meanDamageMitigatedLowMMR) {
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
        this.meanDamageDoneHighMMR = meanDamageDoneHighMMR;
        this.meanDamageDoneMediumMMR = meanDamageDoneMediumMMR;
        this.meanDamageDoneLowMMR = meanDamageDoneLowMMR;
        this.meanBasicAttackDamageHighMMR = meanBasicAttackDamageHighMMR;
        this.meanBasicAttackDamageMediumMMR = meanBasicAttackDamageMediumMMR;
        this.meanBasicAttackDamageLowMMR = meanBasicAttackDamageLowMMR;
        this.meanDamageMitigatedHighMMR = meanDamageMitigatedHighMMR;
        this.meanDamageMitigatedMediumMMR = meanDamageMitigatedMediumMMR;
        this.meanDamageMitigatedLowMMR = meanDamageMitigatedLowMMR;
    }

    public GodData() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getMeanDamageDoneHighMMR() {
        return meanDamageDoneHighMMR;
    }

    public void setMeanDamageDoneHighMMR(Integer meanDamageDoneHighMMR) {
        this.meanDamageDoneHighMMR = meanDamageDoneHighMMR;
    }

    public Integer getMeanDamageDoneMediumMMR() {
        return meanDamageDoneMediumMMR;
    }

    public void setMeanDamageDoneMediumMMR(Integer meanDamageDoneMediumMMR) {
        this.meanDamageDoneMediumMMR = meanDamageDoneMediumMMR;
    }

    public Integer getMeanDamageDoneLowMMR() {
        return meanDamageDoneLowMMR;
    }

    public void setMeanDamageDoneLowMMR(Integer meanDamageDoneLowMMR) {
        this.meanDamageDoneLowMMR = meanDamageDoneLowMMR;
    }

    public Integer getMeanBasicAttackDamageHighMMR() {
        return meanBasicAttackDamageHighMMR;
    }

    public void setMeanBasicAttackDamageHighMMR(Integer meanBasicAttackDamageHighMMR) {
        this.meanBasicAttackDamageHighMMR = meanBasicAttackDamageHighMMR;
    }

    public Integer getMeanBasicAttackDamageMediumMMR() {
        return meanBasicAttackDamageMediumMMR;
    }

    public void setMeanBasicAttackDamageMediumMMR(Integer meanBasicAttackDamageMediumMMR) {
        this.meanBasicAttackDamageMediumMMR = meanBasicAttackDamageMediumMMR;
    }

    public Integer getMeanBasicAttackDamageLowMMR() {
        return meanBasicAttackDamageLowMMR;
    }

    public void setMeanBasicAttackDamageLowMMR(Integer meanBasicAttackDamageLowMMR) {
        this.meanBasicAttackDamageLowMMR = meanBasicAttackDamageLowMMR;
    }

    public Integer getMeanDamageMitigatedHighMMR() {
        return meanDamageMitigatedHighMMR;
    }

    public void setMeanDamageMitigatedHighMMR(Integer meanDamageMitigatedHighMMR) {
        this.meanDamageMitigatedHighMMR = meanDamageMitigatedHighMMR;
    }

    public Integer getMeanDamageMitigatedMediumMMR() {
        return meanDamageMitigatedMediumMMR;
    }

    public void setMeanDamageMitigatedMediumMMR(Integer meanDamageMitigatedMediumMMR) {
        this.meanDamageMitigatedMediumMMR = meanDamageMitigatedMediumMMR;
    }

    public Integer getMeanDamageMitigatedLowMMR() {
        return meanDamageMitigatedLowMMR;
    }

    public void setMeanDamageMitigatedLowMMR(Integer meanDamageMitigatedLowMMR) {
        this.meanDamageMitigatedLowMMR = meanDamageMitigatedLowMMR;
    }
}
