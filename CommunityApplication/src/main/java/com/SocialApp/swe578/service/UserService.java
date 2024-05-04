package com.SocialApp.swe578.service;

import com.SocialApp.swe578.dto.UserRegistrationDto;
import com.SocialApp.swe578.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


public interface UserService extends UserDetailsService {
    //Method without body that returns user take UserRegistrationDto object as param
    User save(UserRegistrationDto registrationDto);
    void delete(String username);
}
