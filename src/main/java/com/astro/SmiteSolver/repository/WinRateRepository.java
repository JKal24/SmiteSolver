package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.totaldata.WinRateRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinRateRepository extends JpaRepository<WinRateRanking, Integer> {
}
