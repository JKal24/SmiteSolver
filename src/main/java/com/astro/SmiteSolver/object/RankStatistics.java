package com.astro.SmiteSolver.object;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RankStatistics {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer tier;

    private Float mmr;

    public RankStatistics() { }
    public RankStatistics(Integer tier, Float mmr) {
        this.tier = tier;
        this.mmr = mmr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Float getMmr() {
        return mmr;
    }

    public void setMmr(Float mmr) {
        this.mmr = mmr;
    }
}
