package com.dbccompany.receitasapp.repository;

import com.dbccompany.receitasapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByUserNameContainingIgnoreCase(String userName);

    @Query("select u from user_recipe u order by u.userName")
    List<UserEntity> listAllUsersOrderByName();

}
