package com.astro.SmiteSolver.entity.totaldata;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "match_data")
public class MatchRecordedData {

    @Id
    private LocalDate date;

    private Integer matchesPlayedHighMMR;

    private Integer matchesPlayedLowMMR;

    public MatchRecordedData(LocalDate date, Integer matchesPlayedHighMMR, Integer matchesPlayedLowMMR) {
        this.date = date;
        this.matchesPlayedHighMMR = matchesPlayedHighMMR;
        this.matchesPlayedLowMMR = matchesPlayedLowMMR;
    }

    public MatchRecordedData() { }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMatchesPlayedHighMMR() {
        return matchesPlayedHighMMR;
    }

    public void setMatchesPlayedHighMMR(Integer matchesPlayedHighMMR) {
        this.matchesPlayedHighMMR = matchesPlayedHighMMR;
    }

    public Integer getMatchesPlayedLowMMR() {
        return matchesPlayedLowMMR;
    }

    public void setMatchesPlayedLowMMR(Integer matchesPlayedLowMMR) {
        this.matchesPlayedLowMMR = matchesPlayedLowMMR;
    }
}
