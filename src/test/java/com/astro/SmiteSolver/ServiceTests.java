package com.astro.SmiteSolver;

import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.service.DataCompilationService;
import com.astro.SmiteSolver.service.MatchParserService;
import com.astro.SmiteSolver.service.PerformanceDataService;
import com.astro.SmiteSolver.service.UpdateService;
import com.astro.smitebasic.api.SmiteAPI;
import com.astro.smitebasic.objects.gamedata.UserInfo;
import com.astro.smitebasic.objects.items.BaseItemInfo;
import com.astro.smitebasic.utils.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTests {

    @Autowired
    private UpdateService updateService;

    @Autowired
    private MatchParserService matchParserService;

    @Autowired
    private DataCompilationService dataCompilationService;

    @Autowired
    private GodNameRepository godNameRepository;

    @Autowired
    private PerformanceDataService performanceDataService;

    @Autowired
    private SmiteAPI api;

    @Test
    public void updateTest() {
        UserInfo[] info = api.getDataUsed();
        System.out.println(info[0]);
    }

    @Test
    public void dataTest() {
        for (BaseItemInfo info : api.getItems(Language.ENGLISH.getLanguageID())) {
            System.out.println(info);
        }
    }

}
