package com.astro.SmiteSolver.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class utils {

    public static final float HIGH_MMR_BOUNDARY = 1680.0f;

    public static LocalDate getComparableDate(int daysBehind) {
        return LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(daysBehind);
    }
}
