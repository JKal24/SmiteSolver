package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.totaldata.BanRateRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRateRepository extends JpaRepository<BanRateRanking, Integer> {
}
