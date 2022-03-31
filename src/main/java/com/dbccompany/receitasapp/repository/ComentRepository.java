package com.dbccompany.receitasapp.repository;

import com.dbccompany.receitasapp.entity.ComentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentRepository extends JpaRepository<ComentEntity, Long> {
}
