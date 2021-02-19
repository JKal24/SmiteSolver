package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.PerformanceDataHighMMR;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceHighMMRRepository extends CrudRepository<PerformanceDataHighMMR, Integer> {
}
