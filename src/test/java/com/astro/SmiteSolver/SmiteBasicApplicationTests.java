package com.astro.SmiteSolver;

import com.astro.SmiteSolver.service.MatchParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmiteBasicApplicationTests {

	@Autowired
	private MatchParserService matchParserService;

	@Test
	void contextLoads() {
		matchParserService.updateData();
	}

}
