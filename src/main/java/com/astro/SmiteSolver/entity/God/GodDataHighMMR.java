package com.astro.SmiteSolver.entity.God;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Entity(name="god_data_high_mmr")
public class GodDataHighMMR extends GodData {

    public GodDataHighMMR(LocalDate date, Integer godID) {
        super(date, godID, 0, 0, 0, new HashMap<>(), new HashMap<>(),
                new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }
}
