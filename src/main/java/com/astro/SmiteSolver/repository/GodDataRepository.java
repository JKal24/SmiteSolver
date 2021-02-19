package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.GodData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GodDataRepository<T extends GodData, ID extends Serializable> extends CrudRepository<T, ID>  {
}
