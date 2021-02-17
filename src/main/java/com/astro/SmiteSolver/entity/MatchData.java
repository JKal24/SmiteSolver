package com.astro.SmiteSolver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "")
public class MatchData {

    @Id
    private LocalDate date;

    private Integer matchesPlayed;

}
