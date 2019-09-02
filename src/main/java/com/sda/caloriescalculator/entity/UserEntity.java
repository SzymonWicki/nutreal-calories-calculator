package com.sda.caloriescalculator.entity;

import com.sda.caloriescalculator.model.UserSex;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surename;
    private String password;
    @Email
    @Column(unique = true)
    private String email;
    @Enumerated(value = EnumType.STRING)
    private UserSex userSex;

    @OneToMany(mappedBy = "user")
    private List<DailyDietEntity> dailyDietEntityList;

    public UserEntity() {
    }

    public UserEntity(String name, String surename, String password, String email, UserSex userSex) {
        this.name = name;
        this.surename = surename;
        this.password = password;
        this.email = email;
        this.userSex = userSex;
    }



    public List<DailyDietEntity> getDailyDietEntityList() {
        return dailyDietEntityList;
    }

    public void setDailyDietEntityList(List<DailyDietEntity> dailyDietEntityList) {
        this.dailyDietEntityList = dailyDietEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSex) {
        this.userSex = userSex;
    }
}
