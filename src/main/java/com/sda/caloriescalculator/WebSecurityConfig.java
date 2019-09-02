package com.sda.caloriescalculator;

import com.sda.caloriescalculator.service.UserDetailsServiceImpl;
import com.sda.caloriescalculator.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;
    private UserEntity appUserRepo;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserEntity appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }


    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .defaultSuccessUrl("/userIndex",true)
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/index")
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/img/**").permitAll()
                .antMatchers("/productsList").hasRole("USER")
                .antMatchers("/dailyDiet").hasRole("USER")
                .antMatchers("/addProduct").hasRole("USER")
                .antMatchers("/addProducts").hasRole("USER")
                .antMatchers("/userIndex").hasRole("USER")
                .antMatchers("user/registerUser").permitAll()
                .antMatchers("/productsInMeal").permitAll()
                .and()
                .csrf().disable();
    }


}
