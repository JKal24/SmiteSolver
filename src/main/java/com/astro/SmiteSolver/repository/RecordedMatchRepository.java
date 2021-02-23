package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.MatchRecordedData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordedMatchRepository extends CrudRepository<MatchRecordedData, Integer> {
}