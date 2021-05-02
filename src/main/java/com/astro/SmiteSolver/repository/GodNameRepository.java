package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.entity.GodName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GodNameRepository extends CrudRepository<GodName, Integer> {
    @Query(value = "SELECT * FROM god_names WHERE god_name=?1",nativeQuery = true)
    GodName findByName(String name);
}
