package com.astro.SmiteSolver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "match_data")
public class MatchData {

    @Id
    private LocalDate date;

    private Integer matchesPlayed;

    public MatchData(LocalDate date, Integer matchesPlayed) {
        this.date = date;
        this.matchesPlayed = matchesPlayed;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }
}
