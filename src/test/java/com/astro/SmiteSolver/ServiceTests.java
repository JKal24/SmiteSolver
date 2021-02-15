package com.astro.SmiteSolver;

import com.astro.SmiteSolver.service.MatchParserService;
import com.astro.SmiteSolver.service.UpdateService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
public class ServiceTests {

    @Autowired
    private UpdateService updateService;

    @Autowired
    private MatchParserService matchParserService;

    // Used to test update service before it went live

    @Test
    public void updateTest() {
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(31), 7.12);
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(6), 7.15);
        updateService.addUpdate(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(9), 7.14);

        assertThat(updateService.isUpdatableDate()).isNotEqualTo(false);

        updateService.cleanUpdates();
        assertThat(updateService.getDaysStored()).isEqualTo(3);
    }

}
