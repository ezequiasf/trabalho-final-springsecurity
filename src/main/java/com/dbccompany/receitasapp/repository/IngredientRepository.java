package com.dbccompany.receitasapp.repository;

import com.dbccompany.receitasapp.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
}
