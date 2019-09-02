package com.sda.caloriescalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/registerUser")
    public String getContactPage() {
        return "registerUser";
    }
}
