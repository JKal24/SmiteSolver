package com.astro.SmiteSolver;

import com.astro.SmiteSolver.data.UpdateService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ServiceTests {

    @Autowired
    private UpdateService updateService;

    @Test
    public void updateTest() {
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(3), 7.12);
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(6), 7.15);
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(9), 7.14);

        Assert.assertTrue(updateService.isUpdatableDate());

        updateService.cleanUpdates(4);
        Assert.assertEquals(1, updateService.getDaysStored());
    }

}
