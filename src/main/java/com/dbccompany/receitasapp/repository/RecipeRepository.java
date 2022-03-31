package com.dbccompany.receitasapp.repository;

import com.dbccompany.receitasapp.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    @Query(value = "select * " +
            "   from recipe r " +
            "   where r.price <= :price", nativeQuery = true)
    List<RecipeEntity> findByMaxPrice(BigDecimal price);

    @Query("select r " +
            "   from recipe r " +
            "   where r.prepareTime <= :prepareTime")
    List<RecipeEntity> findByPrepareTime(Integer prepareTime);

    @Query("select r " +
            "   from recipe r " +
            "   where r.calories <= :calories")
    List<RecipeEntity> findByMaxCalories(BigDecimal calories);

}
