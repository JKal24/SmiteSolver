package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.object.MatchData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDataRepository extends CrudRepository<MatchData, Integer> {
}
