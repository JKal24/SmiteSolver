package com.astro.SmiteSolver;

import com.astro.SmiteSolver.repository.DailyHighMMRDailyGodDataRepository;
import com.astro.SmiteSolver.service.MatchParserService;
import com.astro.smitebasic.api.SmiteAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmiteSolverApplicationTests {

	@Autowired
	private MatchParserService matchParserService;

	@Autowired
	private DailyHighMMRDailyGodDataRepository dailyHighMMRDailyGodDataRepository;

	@Autowired
	private SmiteAPI api;

	@Test
	public void updatesData() {
		 //matchParserService.updateData(1);
	}

	@Test
	public void testUsage() {
		//System.out.println(api.getDataUsed()[0]);
	}
}
