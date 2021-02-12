package com.astro.SmiteSolver.repository;

import com.astro.SmiteSolver.object.God.GodData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GodDataRepository extends CrudRepository<GodData, Integer> {
    @Query(value = "UPDATE god_data SET matchesLowMMR = matchesLowMMR + 1 WHERE god_id =: id")
    public void incrementMatchesLowMMR(@Param("id") Integer god_id);

    @Query(value = "UPDATE god_data SET matchesHighMMR = matchesHighMMR + 1 WHERE god_id =: id")
    public void incrementMatchesHighMMR(@Param("id") Integer god_id);

    @Query(value = "UPDATE god_data SET WinsLowMMR = WinsLowMMR + 1 WHERE god_id =: id")
    public void incrementWinsLowMMR(@Param("id") Integer god_id);

    @Query(value = "UPDATE god_data SET WinsHighMMR = WinsHighMMR + 1 WHERE god_id =: id")
    public void incrementWinsHighMMR(@Param("id") Integer god_id);

    @Query(value = "UPDATE god_data SET lossesLowMMR = lossesLowMMR + 1 WHERE god_id =: id")
    public void incrementLossesLowMMR(@Param("id") Integer god_id);

    @Query(value = "UPDATE god_data SET lossesHighMMR = lossesHighMMR + 1 WHERE god_id =: id")
    public void incrementLossesHighMMR(@Param("id") Integer god_id);

    @Query(value = "UPDATE god_data SET bansLowMMR = bansLowMMR + 1 WHERE god_id =: id")
    public void incrementBansLowMMR(@Param("id") Integer god_id);

    @Query(value = "UPDATE god_data SET bansHighMMR = bansHighMMR + 1 WHERE god_id =: id")
    public void incrementBansHighMMR(@Param("id") Integer god_id);

}
