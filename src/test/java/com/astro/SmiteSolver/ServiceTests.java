package com.astro.SmiteSolver;

import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.service.DataCompilationService;
import com.astro.SmiteSolver.service.MatchParserService;
import com.astro.SmiteSolver.service.DataFetchingService;
import com.astro.SmiteSolver.service.UpdateService;
import com.astro.smitebasic.api.SmiteAPI;
import com.astro.smitebasic.objects.gamedata.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

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
    private DataFetchingService dataFetchingService;

    @Autowired
    private SmiteAPI api;

    @Test
    public void updateTest() {
        UserInfo[] info = api.getDataUsed();
        System.out.println(info[0]);
    }

    @Test
    public void dataTest() {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        map1.put("ok1", 9);
        map1.put("ok2", 3);
        map1.put("ok3", 2);
        map1.put("ok4", 1);
        map1.put("ok5", 6);

        map2.put("no6", 43);
        map2.put("no7", 41);
        map2.put("no8", 49);
        map2.put("no9", 47);
        map2.put("no10", 44);

        Map<String, Integer> updatedMap = dataCompilationService.addNameCountMap(map1, map2.entrySet());
        int checkLast = -1;
        for (Map.Entry<String, Integer> entry : updatedMap.entrySet()) {
            // Assert last entry value less than or equal to new entry value.
        }
    }

}
