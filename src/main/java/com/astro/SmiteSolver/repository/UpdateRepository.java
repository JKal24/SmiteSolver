package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.object.UpdateData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UpdateRepository extends CrudRepository<UpdateData, LocalDate> {
}
