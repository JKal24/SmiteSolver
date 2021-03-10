package com.astro.SmiteSolver;

import com.astro.SmiteSolver.entity.DailyGodDataHighMMR;
import com.astro.SmiteSolver.entity.DailyGodDataLowMMR;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.service.MatchParserService;
import com.astro.SmiteSolver.service.PerformanceDataService;
import com.astro.SmiteSolver.service.UpdateService;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
public class ServiceTests {

    @Autowired
    private UpdateService updateService;

    @Autowired
    private MatchParserService matchParserService;

    @Autowired
    private PerformanceDataService performanceDataService;

    @Autowired
    private GodNameRepository godNameRepository;

    @Test
    public void updateTest() {
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(31), 7.12);
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(6), 7.15);
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(9), 7.14);

        updateService.cleanUpdates();
        assertThat(updateService.getDaysStored()).isEqualTo(2);
    }

    public void GodDataTest() {
        DailyGodDataHighMMR highMMRdata = new DailyGodDataHighMMR(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")), 2323, "Ah Muzen Cab");

        DailyGodDataLowMMR lowMMRdata = new DailyGodDataLowMMR(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")), 2323, "Ah Muzen Cab");


    }

}
