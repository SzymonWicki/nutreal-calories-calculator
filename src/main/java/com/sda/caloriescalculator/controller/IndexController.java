package com.sda.caloriescalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/")
    public String getIndexPageRedirect() {
        return "index";
    }

}

