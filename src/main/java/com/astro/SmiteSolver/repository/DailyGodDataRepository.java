package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.DailyGodData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface DailyGodDataRepository<T extends DailyGodData, ID extends Serializable> extends JpaRepository<T, ID> {

}
