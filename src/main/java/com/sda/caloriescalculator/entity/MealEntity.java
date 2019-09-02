package com.sda.caloriescalculator.entity;

import com.sda.caloriescalculator.model.DailyDiet;
import com.sda.caloriescalculator.model.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "meal")
    private List<ProductEntity> products;

    @ManyToOne
    private DailyDietEntity dailyDiet;

    public MealEntity() {
    }

    public MealEntity(List<ProductEntity> products, DailyDietEntity dailyDiet) {
        this.products = products;
        this.dailyDiet = dailyDiet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public DailyDietEntity getDailyDiet() {
        return dailyDiet;
    }

    public void setDailyDiet(DailyDietEntity dailyDiet) {
        this.dailyDiet = dailyDiet;
    }
}
