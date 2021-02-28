package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.TotalGodDataHighMMR;
import org.springframework.stereotype.Repository;

@Repository
public interface LowMMRPerformanceRepository extends GodPerformanceRepository<TotalGodDataHighMMR, Integer>{
}
