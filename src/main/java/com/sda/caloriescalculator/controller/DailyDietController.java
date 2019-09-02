package com.sda.caloriescalculator.controller;

import com.sda.caloriescalculator.entity.DailyDietEntity;
import com.sda.caloriescalculator.entity.UserEntity;
import com.sda.caloriescalculator.repository.DailyDietRepository;
import com.sda.caloriescalculator.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DailyDietController {

    private DailyDietRepository dailyDietRepository;
    private UserEntityRepository userEntityRepository;

    @Autowired
    public DailyDietController(DailyDietRepository dailyDietRepository, UserEntityRepository userEntityRepository) {
        this.dailyDietRepository = dailyDietRepository;
        this.userEntityRepository = userEntityRepository;
    }

//    @GetMapping("/yourDiet")
//    public String getDietPage() {
//        return "yourDiet";
//    }

    @GetMapping("/dailyDiet")
    public String getDiet(@ModelAttribute DailyDietEntity dailyDietEntity, Model model) {
        try {
            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String userName;
            if (principal instanceof UserEntity) {
                userName = ((UserEntity) principal).getName();
            } else {
                userName = principal.toString();
            }

            final UserEntity userEntitiesByName = userEntityRepository.findUserEntitiesByName(userName);

            dailyDietEntity.setUser(userEntitiesByName);

            model.addAttribute("dailyDiet", dailyDietEntity);

            dailyDietRepository.save(dailyDietEntity);

            return "redirect:/yourDiet";
        }catch (Exception ex){
            return "redirect:/userIndex?dailyDietExist";
        }
    }
    @GetMapping("/yourDiet")
    public String getMydiet(Model model){
        try {
            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String userName;
            if (principal instanceof UserEntity) {
                userName = ((UserEntity) principal).getName();
            } else {
                userName = principal.toString();
            }

            final UserEntity userEntitiesByName = userEntityRepository.findUserEntitiesByName(userName);

        List<DailyDietEntity> all = dailyDietRepository.findByUserId(userEntitiesByName.getId());
        model.addAttribute("dietList",all);
        return "yourDiet";
        }catch (Exception ex){
            return "redirect:/userIndex?dailyDietExist";
        }

    }
}
