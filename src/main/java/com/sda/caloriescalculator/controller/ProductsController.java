package com.sda.caloriescalculator.controller;

import com.sda.caloriescalculator.entity.MealEntity;
import com.sda.caloriescalculator.entity.ProductEntity;
import com.sda.caloriescalculator.model.ListWrapper;
import com.sda.caloriescalculator.model.ProductCategory;
import com.sda.caloriescalculator.repository.MealRepository;
import com.sda.caloriescalculator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class ProductsController {

    private ProductRepository productRepository;
    private MealRepository mealRepository;

    @Autowired
    public ProductsController(ProductRepository productRepository, MealRepository mealRepository) {
        this.productRepository = productRepository;
        this.mealRepository = mealRepository;
    }


    @GetMapping("/productsList")
    public String getProductsPage(Model model,@RequestParam(name = "id") Long id) {
        List<ProductEntity> all = productRepository.findAll();
        model.addAttribute("productsList", all);
        model.addAttribute("id" ,id);

        return "products";
    }

//    @PostMapping("/productsSearch")
//    public String FindProductsByName(@RequestParam(name = "name") String name,
//                                     Model model, @RequestParam(name = "id") String id) {
//        model.addAttribute("name",name);
//        model.addAttribute("id",id);
//
//        return "productsSearch";
//
//    }")

//    @GetMapping("/addProducts")
//    public String addProductForm(Model model, @RequestParam(name = "id") Long id,@ModelAttribute ProductEntity productEntity) {
//        Optional<MealEntity> byId = mealRepository.findById(id);
//        if(byId.isPresent()){
//            productEntity.setMeal(byId.get());
//            productRepository.save(productEntity);
//        }
//        model.addAttribute("productCategory", ProductCategory.values());
//        return "addProducts";
//    }

    @GetMapping("/addProducts")
    public String addProductForm(Model model) {
        model.addAttribute("products", ProductCategory.values());
        model.addAttribute("productCategory", ProductCategory.values());
        return "addProducts";
    }

    @PostMapping("/addProducts")
    public String addProduct(@ModelAttribute ProductEntity productEntity, Model model) {
        ProductEntity productEntity1 = new ProductEntity();
        model.addAttribute("products", ProductCategory.values());
        productEntity1.setMeal(productEntity.getMeal());
        productEntity1.setCalories(productEntity.getCalories());
        productEntity1.setCarbs(productEntity.getCarbs());
        productEntity1.setFats(productEntity.getFats());
        productEntity1.setCategory(ProductCategory.valueOf(productEntity.getCategory().name()));
        productEntity1.setProtein(productEntity.getProtein());
        productEntity1.setName(productEntity.getName());
        productRepository.save(productEntity1);
        return "redirect:/addProducts";
    }

    @PostMapping("/addProductToMeal")
    public String addProductsToMeal(@RequestParam(name = "name") String name, @RequestParam(name = "id") Long id){
        try {

        ProductEntity productEntityByName = productRepository.findByName(name);
        Optional<MealEntity> meal = mealRepository.findById(id);
        if(meal.isPresent()){
            productEntityByName.setMeal(meal.get());
            productRepository.save(productEntityByName);
            mealRepository.save(meal.get());
        }
        } catch (Exception ex) {
            return "redirect:/productsList?id=" + id ;

        }

        return "redirect:/userIndex";
    }
}

