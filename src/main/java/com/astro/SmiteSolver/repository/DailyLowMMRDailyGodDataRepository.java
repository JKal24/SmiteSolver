package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.DailyGodDataLowMMR;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DailyLowMMRDailyGodDataRepository extends DailyGodDataRepository<DailyGodDataLowMMR, Integer> {
    @Query(value = "SELECT * FROM god_data_low_mmr g WHERE g.date:=chosenDate", nativeQuery = true)
    public Iterable<DailyGodDataLowMMR> findByDate(@Param("chosenDate") LocalDate chosenDate);

    @Query(value = "SELECT * FROM god_data_low_mmr g WHERE g.godID:=chosenGodID", nativeQuery = true)
    public Iterable<DailyGodDataLowMMR> findByGodID(@Param("chosenGodID") Integer godID);
}
