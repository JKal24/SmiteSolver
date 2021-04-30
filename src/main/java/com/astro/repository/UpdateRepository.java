package com.astro.repository;

import com.astro.entity.UpdateData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UpdateRepository extends CrudRepository<UpdateData, LocalDate> {
}
