package com.SocialApp.swe578.web;

import com.SocialApp.swe578.model.User;
import com.SocialApp.swe578.repository.UserRepository;
import com.SocialApp.swe578.service.CommunityService;
import com.SocialApp.swe578.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    private CommunityService communityService;
    @Autowired
    public UserController(UserService userService, UserRepository userRepository, CommunityService communityService){
        this.userService=userService;
        this.userRepository=userRepository;
    this.communityService = communityService;}

    @GetMapping("/{username}")
    public String showUserProfile(@PathVariable String username, Model model){
        User user = userRepository.findByEmail(username);
        model.addAttribute("user", user);
        return "userProfile";
    }

    @PostMapping("/{username}/deleteProfile")
    public String deleteProfile(@PathVariable String  username){
        communityService.removeUserFromCommunities();
        userService.delete(username);
        return "redirect:/login";
    }


}
