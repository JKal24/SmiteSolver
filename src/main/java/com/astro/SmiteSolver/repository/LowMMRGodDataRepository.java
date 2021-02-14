package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.God.GodDataLowMMR;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LowMMRGodDataRepository extends CrudRepository<GodDataLowMMR, Integer> {

}
