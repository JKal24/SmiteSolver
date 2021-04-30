package com.astro.repository;

import com.astro.entity.TotalGodDataHighMMR;
import org.springframework.stereotype.Repository;

@Repository
public interface HighMMRPerformanceRepository extends GodPerformanceRepository<TotalGodDataHighMMR, Integer> {
}
