package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.GodName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GodNameRepository extends CrudRepository<GodName, Integer> {
}
