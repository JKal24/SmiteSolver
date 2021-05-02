package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.BaseItemName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemNameRepository extends CrudRepository<BaseItemName, Integer> {
}
