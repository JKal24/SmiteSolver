package com.astro.SmiteSolver.object;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table()
public class GodMatchData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private LocalDate date;

    private Integer godID;

    private Integer itemID1;

    private Integer itemID2;

    private Integer itemID3;

    private Integer itemID4;

    private Integer itemID5;

    private Integer itemID6;

    private Integer activeID1;

    private Integer activeID2;

    private Boolean matchResult;

    private String skinName;

    private Integer damageDone;

    private Integer basicAttackDamage;

    private Integer damageMitigated;

    public GodMatchData(LocalDate date, Integer godID, Integer itemID1, Integer itemID2, Integer itemID3, Integer itemID4,
                        Integer itemID5, Integer itemID6, Integer activeID1, Integer activeID2, Boolean matchResult,
                        String skinName, Integer damageDone, Integer basicAttackDamage, Integer damageMitigated) {
        this.date = date;
        this.godID = godID;
        this.itemID1 = itemID1;
        this.itemID2 = itemID2;
        this.itemID3 = itemID3;
        this.itemID4 = itemID4;
        this.itemID5 = itemID5;
        this.itemID6 = itemID6;
        this.activeID1 = activeID1;
        this.activeID2 = activeID2;
        this.matchResult = matchResult;
        this.skinName = skinName;
        this.damageDone = damageDone;
        this.basicAttackDamage = basicAttackDamage;
        this.damageMitigated = damageMitigated;
    }

    public GodMatchData() { }

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

    public Integer getItemID1() {
        return itemID1;
    }

    public void setItemID1(Integer itemID1) {
        this.itemID1 = itemID1;
    }

    public Integer getItemID2() {
        return itemID2;
    }

    public void setItemID2(Integer itemID2) {
        this.itemID2 = itemID2;
    }

    public Integer getItemID3() {
        return itemID3;
    }

    public void setItemID3(Integer itemID3) {
        this.itemID3 = itemID3;
    }

    public Integer getItemID4() {
        return itemID4;
    }

    public void setItemID4(Integer itemID4) {
        this.itemID4 = itemID4;
    }

    public Integer getItemID5() {
        return itemID5;
    }

    public void setItemID5(Integer itemID5) {
        this.itemID5 = itemID5;
    }

    public Integer getItemID6() {
        return itemID6;
    }

    public void setItemID6(Integer itemID6) {
        this.itemID6 = itemID6;
    }

    public Integer getActiveID1() {
        return activeID1;
    }

    public void setActiveID1(Integer activeID1) {
        this.activeID1 = activeID1;
    }

    public Integer getActiveID2() {
        return activeID2;
    }

    public void setActiveID2(Integer activeID2) {
        this.activeID2 = activeID2;
    }

    public Boolean getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(Boolean matchResult) {
        this.matchResult = matchResult;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public Integer getDamageDone() {
        return damageDone;
    }

    public void setDamageDone(Integer damageDone) {
        this.damageDone = damageDone;
    }

    public Integer getBasicAttackDamage() {
        return basicAttackDamage;
    }

    public void setBasicAttackDamage(Integer basicAttackDamage) {
        this.basicAttackDamage = basicAttackDamage;
    }

    public Integer getDamageMitigated() {
        return damageMitigated;
    }

    public void setDamageMitigated(Integer damageMitigated) {
        this.damageMitigated = damageMitigated;
    }
}
