package com.astro.repository;

import com.astro.entity.TotalGodDataLowMMR;
import org.springframework.stereotype.Repository;

@Repository
public interface LowMMRPerformanceRepository extends GodPerformanceRepository<TotalGodDataLowMMR, Integer>{
}
