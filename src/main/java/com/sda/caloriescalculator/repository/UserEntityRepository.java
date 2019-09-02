package com.sda.caloriescalculator.repository;

import com.sda.caloriescalculator.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntitiesByEmail(String name);
    UserEntity findUserEntitiesById(Long id);
    UserEntity findUserEntitiesByName(String name);
}

