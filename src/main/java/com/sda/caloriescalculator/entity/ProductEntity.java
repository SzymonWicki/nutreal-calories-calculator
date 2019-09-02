package com.sda.caloriescalculator.entity;

import com.sda.caloriescalculator.model.Meal;
import com.sda.caloriescalculator.model.ProductCategory;

import javax.persistence.*;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double calories;
    private String name;
    private Double fats;
    private Double carbs;
    private Double protein;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @ManyToOne
    private MealEntity meal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public MealEntity getMeal() {
        return meal;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }

    public ProductEntity() {
    }

    public ProductEntity(Double calories, String name, Double fats, Double carbs, Double protein, ProductCategory category) {
        this.calories = calories;
        this.name = name;
        this.fats = fats;
        this.carbs = carbs;
        this.protein = protein;
        this.category = category;
    }
}
