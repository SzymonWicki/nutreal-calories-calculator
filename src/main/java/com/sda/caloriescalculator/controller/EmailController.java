package com.sda.caloriescalculator.controller;


import com.sda.caloriescalculator.repository.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }
    @RequestMapping("/template")
    public String send(String name, String email, String description) {
        Context context = new Context();
        context.setVariable("header","Wiadomość od: " + name);
        context.setVariable("email","Email: " + email);
        context.setVariable("description", description);
        String body = templateEngine.process("template", context);
        emailSender.sendEmail("nutreal88@gmail.com", "CodeCouple Newsletter", body);
        return "index";
    }
}
