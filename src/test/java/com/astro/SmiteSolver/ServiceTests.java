package com.astro.SmiteSolver;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.*;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.service.MatchParserService;
import com.astro.SmiteSolver.service.PerformanceDataService;
import com.astro.SmiteSolver.service.UpdateService;

import com.astro.smitebasic.api.SmiteAPI;
import com.astro.smitebasic.objects.gamedata.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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

    @Autowired
    private SmiteAPI api;

    @Test
    public void updateTest() {
        UserInfo[] info = api.getDataUsed();
        System.out.println(info[0]);
    }

    @Test
    public void compileTest() {
        updateService.tempDel();
        matchParserService.updateData();
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

        for (TotalGodDataHighMMR dataHighMMR : highMMRList) {
            System.out.println(dataHighMMR);
        }
    }

    private List<DailyGodDataHighMMR> makeDailyHighMMRGodData(LocalDate date, int len) {
        List<DailyGodDataHighMMR> godList = new ArrayList<>();
        GodName god = getRandomGod();
        int godID = god.getGodID();
        String name = god.getGodName();

        for (int parse = 0; parse < len; parse++) {
            godList.add(new DailyGodDataHighMMR(date, godID, name, getRandomParameters(100, 10),
                    getRandomParameters(50, 5), getRandomParameters(75, 15), getRandomMap(5),
                    getItemRandomMap(5), getRandomMap(5), getRandomParameters(55000, 5000),
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
                    getItemRandomMap(5), getRandomMap(5), getRandomParameters(55000, 5000),
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

    private Map<Item, Integer> getItemRandomMap(int len) {
        Map<Item, Integer> items = new HashMap<>();
        String initial = "item";

        for (int parse = 0; parse < len; parse++) {
            items.put(new Item(parse, initial + parse), parse);
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
