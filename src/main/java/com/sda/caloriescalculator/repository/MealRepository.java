package com.sda.caloriescalculator.repository;

import com.sda.caloriescalculator.entity.MealEntity;
import com.sda.caloriescalculator.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {
}
