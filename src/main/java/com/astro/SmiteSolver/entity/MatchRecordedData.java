package com.astro.SmiteSolver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "match_data")
public class MatchRecordedData {

    @Id
    private LocalDate date;

    private Integer matchesPlayed;

    public MatchRecordedData(LocalDate date, Integer matchesPlayed) {
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
