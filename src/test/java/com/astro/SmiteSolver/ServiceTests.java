package com.astro.SmiteSolver;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.*;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.service.MatchParserService;
import com.astro.SmiteSolver.service.PerformanceDataService;
import com.astro.SmiteSolver.service.UpdateService;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void compileTest() {
        matchParserService.compileGodList();
        Map<Integer, DailyGodDataHighMMR> godMapHighMMR = getHighMMRGodData();
        Map<Integer, DailyGodDataLowMMR> godMapLowMMR = getLowMMRGodData();

        assertThat(godMapHighMMR).isNotEmpty();
        assertThat(godMapLowMMR).isNotEmpty();

        performanceDataService.compileGodData(godMapHighMMR, godMapLowMMR, 0, 0);
    }

    @Test
    public void godDataTest() {
        for (DailyGodDataHighMMR dataHighMMR : makeDailyHighMMRGodData(utils.getComparableDate(5), 10)) {
            performanceDataService.addHighMMRGodData(dataHighMMR.getGodID(), dataHighMMR, 50, 30);
        }

        for (DailyGodDataLowMMR dataLowMMR : makeDailyLowMMRGodData(utils.getComparableDate(7), 10)) {
            performanceDataService.addLowMMRGodData(dataLowMMR.getGodID(), dataLowMMR, 50, 30);
        }

        List<TotalGodDataHighMMR> highMMRList = performanceDataService.getTotalHighMMRData();
        List<TotalGodDataLowMMR> lowMMRList = performanceDataService.getTotalLowMMRData();
    }

    private List<DailyGodDataHighMMR> makeDailyHighMMRGodData(LocalDate date, int len) {
        List<DailyGodDataHighMMR> godList = new ArrayList<>();

        for (int parse = 0; parse < len; parse++) {
            GodName god = getRandomGod();
            int godID = god.getGodID();
            String name = god.getGodName();

            godList.add(new DailyGodDataHighMMR(date, godID, name, getRandomParameters(100, 10),
                    getRandomParameters(50, 5), getRandomParameters(75, 15), getRandomMap(5),
                    getRandomMap(5), getRandomMap(5), getRandomParameters(55000, 5000),
                    getRandomParameters(40000, 3000), getRandomParameters(60000, 10000)
                    ));
        }
        return godList;
    }

    private List<DailyGodDataLowMMR> makeDailyLowMMRGodData(LocalDate date, int len) {
        List<DailyGodDataLowMMR> godList = new ArrayList<>();

        for (int parse = 0; parse < len; parse++) {
            GodName god = getRandomGod();
            int godID = god.getGodID();
            String name = god.getGodName();

            godList.add(new DailyGodDataLowMMR(date, godID, name, getRandomParameters(100, 10),
                    getRandomParameters(50, 5), getRandomParameters(75, 15), getRandomMap(5),
                    getRandomMap(5), getRandomMap(5), getRandomParameters(55000, 5000),
                    getRandomParameters(40000, 3000), getRandomParameters(60000, 10000)
            ));
        }
        return godList;
    }

    private GodName getRandomGod() {
        try {
            List<GodName> names = (List<GodName>) godNameRepository.findAll();
            return names.get((int) (Math.random() * names.size()));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return new GodName(0, "");
    }

    private int getRandomParameters(int minimum, int upperVariance) {
        return (int) (minimum + (Math.random() * (upperVariance - minimum)));
    }

    private Map<String, Integer> getRandomMap(int len) {
        Map<String, Integer> items = new HashMap<>();
        String initial = "item";

        for (int parse = 0; parse < len; parse++) {
            items.put(initial + parse, parse);
        }
        return items;
    }

    private Map<Integer, DailyGodDataHighMMR> getHighMMRGodData() {
        Map<Integer, DailyGodDataHighMMR> godMap = new HashMap<Integer, DailyGodDataHighMMR>();

        for (GodName name : godNameRepository.findAll()) {
            godMap.put(name.getGodID(), new DailyGodDataHighMMR(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")), name.getGodID(), name.getGodName()));
        }

        return godMap;
    }

    private Map<Integer, DailyGodDataLowMMR> getLowMMRGodData() {
        Map<Integer, DailyGodDataLowMMR> godMap = new HashMap<Integer, DailyGodDataLowMMR>();

        for (GodName name : godNameRepository.findAll()) {
            godMap.put(name.getGodID(), new DailyGodDataLowMMR(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")), name.getGodID(), name.getGodName()));
        }

        return godMap;
    }
}
