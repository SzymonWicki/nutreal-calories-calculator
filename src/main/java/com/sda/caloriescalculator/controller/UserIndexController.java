package com.sda.caloriescalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserIndexController {

    @GetMapping("/userIndex")
    public String getUserIndexPage() {
        return "userIndex";
    }
    @PostMapping("/userIndex")
    public String getUserIndexPagePost() {
        return "userIndex";
    }
}
