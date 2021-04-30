package com.astro.repository;

import com.astro.entity.DailyGodData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface DailyGodDataRepository<T extends DailyGodData, ID extends Serializable> extends CrudRepository<T, ID>  {

}
