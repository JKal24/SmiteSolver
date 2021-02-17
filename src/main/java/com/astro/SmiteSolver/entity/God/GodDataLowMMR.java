package com.astro.SmiteSolver.entity.God;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Entity(name="god_data_low_mmr")
public class GodDataLowMMR extends GodData {

    public GodDataLowMMR(LocalDate date, Integer godID, String godName) {
        super(date, godID, godName, 0, 0, 0, new HashMap<>(), new HashMap<>(),
                new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }
}
