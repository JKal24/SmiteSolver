package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.totaldata.TotalGodDataHighMMR;
import org.springframework.stereotype.Repository;

@Repository
public interface HighMMRPerformanceRepository extends GodPerformanceRepository<TotalGodDataHighMMR, Integer> {
}
