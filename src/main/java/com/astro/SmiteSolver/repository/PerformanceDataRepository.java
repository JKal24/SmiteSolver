package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.object.PerformanceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceDataRepository extends CrudRepository<PerformanceData, Integer> {
}
