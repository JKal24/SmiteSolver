package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.object.GodData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GodDataRepository extends CrudRepository<GodData, Integer> {
}
