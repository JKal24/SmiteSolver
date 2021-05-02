package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.TotalGodData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GodPerformanceRepository<T extends TotalGodData, ID extends Serializable> extends CrudRepository<T, ID> {
}
