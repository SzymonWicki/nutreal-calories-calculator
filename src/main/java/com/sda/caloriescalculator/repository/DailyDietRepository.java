package com.sda.caloriescalculator.repository;

import com.sda.caloriescalculator.entity.DailyDietEntity;
import com.sda.caloriescalculator.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyDietRepository extends JpaRepository<DailyDietEntity, Long> {

    DailyDietEntity findByDate(LocalDate date);
    List<DailyDietEntity> findByUserId(Long id);
}
