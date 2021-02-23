package com.astro.SmiteSolver.entity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Entity(name="god_data_high_mmr")
public class DailyGodDataHighMMR extends DailyGodData {

    public DailyGodDataHighMMR(LocalDate date, Integer godID, String godName) {
        super(date, godID, godName, 0, 0, 0, new HashMap<>(), new HashMap<>(),
                new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }
}