package com.sda.caloriescalculator.controller;

import com.sda.caloriescalculator.entity.ProductEntity;
import com.sda.caloriescalculator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AddProductToUser {
    private ProductRepository productRepository;

    @Autowired
    public AddProductToUser(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/user/addProduct")

    public String addProductToUser(@RequestParam(name = "id") Long id, Model model) {
        Optional<ProductEntity> productEntity =productRepository.findById(id);
        model.addAttribute("product",productEntity);
        return "addWeigthProduct";
    }

}
