package com.astro.repository;

import com.astro.entity.DailyGodDataHighMMR;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DailyHighMMRDailyGodDataRepository extends DailyGodDataRepository<DailyGodDataHighMMR, Integer> {
    @Query(value = "SELECT * FROM god_data_high_mmr g WHERE g.date:=chosenDate", nativeQuery = true)
    public Iterable<DailyGodDataHighMMR> findByDate(@Param("chosenDate") LocalDate chosenDate);

    @Query(value = "SELECT * FROM god_data_high_mmr g WHERE g.godID:=chosenGodID", nativeQuery = true)
    public Iterable<DailyGodDataHighMMR> findByGodID(@Param("chosenGodID") Integer godID);
}
