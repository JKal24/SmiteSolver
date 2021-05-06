package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.totaldata.TotalGodDataLowMMR;
import org.springframework.stereotype.Repository;

@Repository
public interface LowMMRPerformanceRepository extends GodPerformanceRepository<TotalGodDataLowMMR, Integer>{
}
