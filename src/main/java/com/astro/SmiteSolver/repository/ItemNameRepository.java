package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.auxillary.BaseItemName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemNameRepository extends JpaRepository<BaseItemName, Integer> {
}
