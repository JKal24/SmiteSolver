package com.astro.SmiteSolver;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.dailydata.DailyGodDataHighMMR;
import com.astro.SmiteSolver.repository.DailyHighMMRDailyGodDataRepository;
import com.astro.SmiteSolver.service.MatchParserService;
import com.astro.smitebasic.api.SessionService;
import com.astro.smitebasic.api.SmiteAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SmiteSolverApplicationTests {

	@Autowired
	private MatchParserService matchParserService;

	@Autowired
	private DailyHighMMRDailyGodDataRepository dailyHighMMRDailyGodDataRepository;

	@Autowired
	private SmiteAPI api;

	@Autowired
	private SessionService sessionService;

	@Test
	public void updatesData() {
		 matchParserService.updateData(2);
	}

	@Test
	public void testUsage() {
//		System.out.println(api.getDataUsed()[0]);
	}

	@Test
	public void testDailyData() {
		DailyGodDataHighMMR dataHighMMR = new DailyGodDataHighMMR(utils.getComparableDate(0), 0, "Example");
		Map<String, Integer> actives = new HashMap<>();
		actives.put("Example_Item", 1);
		dataHighMMR.setPopularActives(actives);

		dailyHighMMRDailyGodDataRepository.save(dataHighMMR);

		for (DailyGodDataHighMMR dataHighMMR1 : dailyHighMMRDailyGodDataRepository.findAll()) {
			if (dataHighMMR.getGodName().equals(dataHighMMR1.getGodName())) {
				Assertions.assertSame(dataHighMMR.getPopularActives().get("Example_Item"),dataHighMMR1.getPopularActives().get("Example_Item"));
				dailyHighMMRDailyGodDataRepository.delete(dataHighMMR1);
			}
		}
	}

}
