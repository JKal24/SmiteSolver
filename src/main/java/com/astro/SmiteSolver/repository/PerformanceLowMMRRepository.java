package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.PerformanceDataLowMMR;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceLowMMRRepository extends CrudRepository<PerformanceDataLowMMR, Integer> {
}
