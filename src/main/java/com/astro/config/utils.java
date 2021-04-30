package com.astro.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class utils {

    public static final float HIGH_MMR_BOUNDARY = 1680.0f;

    public static LocalDate getComparableDate(int daysBehind) {
        return LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(daysBehind);
    }

    public static Integer makeDailyDataID(Integer godID, LocalDate date) {
        return date.getDayOfMonth() + date.getMonthValue() + date.getYear() + godID;
    }

    public static int roundZero(int val) {
        return val == 0 ? 1 : val;
    }

    public static double roundZero(double val) {
        return val == 0.0 ? 1.0 : val;
    }
}
