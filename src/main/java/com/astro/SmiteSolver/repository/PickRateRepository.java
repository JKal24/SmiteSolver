package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.totaldata.PickRateRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickRateRepository extends JpaRepository<PickRateRanking, Integer> {
}
