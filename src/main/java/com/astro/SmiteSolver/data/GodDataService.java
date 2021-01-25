package com.astro.SmiteSolver.data;

import com.astro.SmiteSolver.object.GodData;
import com.astro.SmiteSolver.repository.GodDataRepository;
import com.astro.smitebasic.objects.player.matches.PlayerMatchData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class GodDataService {

    @Autowired
    private GodDataRepository godDataRepository;

    public void configureGodData(Integer mmr, Integer godID, List<String> popularItems, List<String> popularActives,Integer winStatus,
                                Integer banned, Integer damageDone, Integer basicAttackDamage, Integer damageMitigated) {
        GodData godData = godDataRepository.findById(godID).get();
        if(mmr < 1400) {

        } else if (mmr >= 1400 && mmr < 1800) {

        } else {

        }
    }

    public List<String> getPlayerItems(PlayerMatchData data) {
        return Arrays.asList(data.getItemPurch1(), data.getItemPurch2(), data.getItemPurch3(),
                data.getItemPurch4(), data.getItemPurch5(), data.getItemPurch6());
    }

    public List<String> getPlayerActives(PlayerMatchData data) {
        return Arrays.asList(data.getItemActive1(), data.getItemActive2());
    }

}
