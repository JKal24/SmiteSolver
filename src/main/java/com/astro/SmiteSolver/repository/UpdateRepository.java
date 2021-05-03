package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.UpdateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UpdateRepository extends JpaRepository<UpdateData, LocalDate> {
}
