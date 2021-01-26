package com.astro.SmiteSolver.object;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GodNames {

    @Id
    private Integer godID;

    private String godName;

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
