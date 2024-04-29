package com.SocialApp.swe578.service;

import com.SocialApp.swe578.dto.UserRegistrationDto;
import com.SocialApp.swe578.model.Role;
import com.SocialApp.swe578.model.User;
import com.SocialApp.swe578.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private UserRepository userRepository;
    //cntr injection
    public UserServiceImpl(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw  new UsernameNotFoundException("Invalid Username Or Password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
    //get the user creds for the user authenticated on the current session

    public User getAuthUser(){
    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findByEmail(principal.getUsername());
        return user;
     }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
       return  roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

}
