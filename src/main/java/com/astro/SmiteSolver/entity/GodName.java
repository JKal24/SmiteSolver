package com.astro.SmiteSolver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="god_names")
public class GodName {

    @Id
    private Integer godID;

    private String godName;

    public GodName(Integer godID, String godName) {
        this.godID = godID;
        this.godName = godName;
    }

    public GodName() { }

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
}
