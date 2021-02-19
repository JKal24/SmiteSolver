package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.GodNames;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GodNamesRepository extends CrudRepository<GodNames, Integer> {
}
