package com.astro.SmiteSolver.entity;

import javax.persistence.Id;
import java.time.LocalDate;

public class MatchData {

    @Id
    private LocalDate date;

    private Integer matchesPlayed;

}
