package com.sda.caloriescalculator.controller;

import com.sda.caloriescalculator.entity.DailyDietEntity;
import com.sda.caloriescalculator.entity.MealEntity;
import com.sda.caloriescalculator.entity.ProductEntity;
import com.sda.caloriescalculator.model.Meal;
import com.sda.caloriescalculator.model.Product;
import com.sda.caloriescalculator.repository.DailyDietRepository;
import com.sda.caloriescalculator.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@Controller
public class MealController {
    private DailyDietRepository dailyDietRepository;
    private MealRepository mealRepository;

    @Autowired
    public MealController(DailyDietRepository dailyDietRepository, MealRepository mealRepository) {
        this.dailyDietRepository = dailyDietRepository;
        this.mealRepository = mealRepository;
    }

    @GetMapping("/addMeal")
    public String addProductToMeal(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "addMeal";
    }

    @PostMapping("/addMeal")
    public String addMealToDiet(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name) {
        MealEntity mealEntity = new MealEntity();
        Optional<DailyDietEntity> diet = dailyDietRepository.findById(id);
        if (diet.isPresent()) {
            mealEntity.setName(name);
            mealEntity.setDailyDiet(diet.get());
            mealRepository.save(mealEntity);
        }
        return "redirect:/yourDiet";
    }

    @GetMapping("/dietDetalis")
    public String getDietDetalis(@RequestParam Long id, Model model) {
        Optional<DailyDietEntity> byId = dailyDietRepository.findById(id);
        if (byId.isPresent()) {
            List<MealEntity> meals = byId.get().getMeals();
            model.addAttribute("mealList", meals);
        }
        return "dietDetalis";
    }
    @GetMapping("/productsInMeal")
    public String productsInMeal(@RequestParam Long id, Model model){
        Optional<MealEntity> mealEntity = mealRepository.findById(id);
        if(mealEntity.isPresent()){
            List<ProductEntity> productEntities = mealEntity.get().getProducts();
            double callories = 0;
            for (int i = 0; i <productEntities.size() ; i++) {
              callories += productEntities.get(i).getCalories();
            }
            model.addAttribute("productsInMeal",productEntities);
            model.addAttribute("callories",callories);
        }
        return "productsInMeal";
    }

}
