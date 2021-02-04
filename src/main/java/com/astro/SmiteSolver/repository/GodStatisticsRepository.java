package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.object.GodStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GodStatisticsRepository extends CrudRepository<GodStatistics, Integer> {
}
