package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.God.GodDataLowMMR;
import org.springframework.stereotype.Repository;

@Repository
public interface LowMMRGodDataRepository extends GodDataRepository<GodDataLowMMR, Integer> {

}
