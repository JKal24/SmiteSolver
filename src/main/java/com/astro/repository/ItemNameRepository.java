package com.astro.repository;

import com.astro.entity.BaseItemName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemNameRepository extends CrudRepository<BaseItemName, Integer> {
}
