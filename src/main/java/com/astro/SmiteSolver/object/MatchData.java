package com.astro.SmiteSolver.object;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "match_data")
public class MatchData {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer matchID;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Integer> godBansByID;

    private List<Float> mmr;

    private Integer winningSide;

    public MatchData(Integer matchID, LocalDate date, List<Integer> godBansByID, List<Float> mmr, Integer winningSide) {
        this.matchID = matchID;
        this.date = date;
        this.godBansByID = godBansByID;
        this.mmr = mmr;
        this.winningSide = winningSide;
    }

    public MatchData() { }

    public Integer getId() {
        return id;
    }

    public Integer getMatchID() {
        return matchID;
    }

    public void setMatchID(Integer matchID) {
        this.matchID = matchID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getGodBansByID() {
        return godBansByID;
    }

    public void setGodBansByID(List<Integer> godBansByID) {
        this.godBansByID = godBansByID;
    }

    public List<Float> getMmr() {
        return mmr;
    }

    public void setMmr(List<Float> mmr) {
        this.mmr = mmr;
    }

    public Integer getWinningSide() {
        return winningSide;
    }

    public void setWinningSide(Integer winningSide) {
        this.winningSide = winningSide;
    }
}
