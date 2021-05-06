package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.totaldata.TotalGodData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GodPerformanceRepository<T extends TotalGodData, ID extends Serializable> extends JpaRepository<T, ID> {
}
