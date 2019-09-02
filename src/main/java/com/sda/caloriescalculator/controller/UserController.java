package com.sda.caloriescalculator.controller;

import com.sda.caloriescalculator.entity.UserEntity;
import com.sda.caloriescalculator.model.UserSex;
import com.sda.caloriescalculator.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.RollbackException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class UserController {
    private UserEntityRepository userEntityRepository;

    @Autowired
    public UserController(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/user/registerUser")
    public String getRegisterPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            /* The user is logged in :) */
            return "forward:/userIndex";
        }
        model.addAttribute("userSex", UserSex.values());
        return "registerUser";

    }

    @PostMapping("/user/registerUser")
    public String registerUser(UserEntity userEntity) {

        try {
            UserEntity user = new UserEntity();
            user.setEmail(userEntity.getEmail());
            user.setName(userEntity.getName());
            user.setSurename(userEntity.getSurename());
            user.setPassword(passwordEncoder().encode(userEntity.getPassword()));
            user.setUserSex(UserSex.valueOf(userEntity.getUserSex().name()));
            userEntityRepository.save(user);
            return "redirect:/login";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:/user/registerUser?emailExistError";
        } catch (NullPointerException ex) {
            return "redirect:/user/registerUser?someFieldIsEmpty";
        } catch (TransactionSystemException ex) {
            return "redirect:/user/registerUser?badEmail";
        }

    }

}
