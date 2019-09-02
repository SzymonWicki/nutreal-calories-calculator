package com.sda.caloriescalculator.entity;

import com.sda.caloriescalculator.model.Meal;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DailyDietEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "dailyDiet")
    private List<MealEntity> meals;

    @ManyToOne
    private UserEntity user;

//    @Column(unique = true)
    @CreationTimestamp
    private LocalDate date;

    public DailyDietEntity() {
    }


    public DailyDietEntity(List<MealEntity> meals, UserEntity user) {
        this.meals = meals;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MealEntity> getMeals() {
        return meals;
    }

    public void setMeals(List<MealEntity> meals) {
        this.meals = meals;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
