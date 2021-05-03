package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.MatchRecordedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RecordedMatchRepository extends JpaRepository<MatchRecordedData, LocalDate> {
}
