package com.astro.repository;

import com.astro.entity.MatchRecordedData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RecordedMatchRepository extends CrudRepository<MatchRecordedData, LocalDate> {
}
