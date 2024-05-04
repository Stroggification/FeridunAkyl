package com.SocialApp.swe578.web;

import com.SocialApp.swe578.dto.CommunityDto;
import com.SocialApp.swe578.model.Post;
import com.SocialApp.swe578.service.CommunityService;
import com.SocialApp.swe578.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {

    private CommunityService communityService;


    private PostService postService;
    @Autowired
    public CommunityController(CommunityService communityService, PostService postService) {
        this.communityService = communityService;
        this.postService = postService;
    }
    @ModelAttribute("community")
    public CommunityDto communityDto(){return new CommunityDto();}

    @GetMapping("/{communityName}")
    public  String displayCommunityByName(@PathVariable String communityName,Model model, Model model2){
        List<String> usernames =  communityService.listSubscribers(communityName);
        List<Post> postList = postService.listAllPostsInCommunity(communityName);
        Collections.reverse(postList);
        model.addAttribute("usernames", usernames);
        model2.addAttribute("post", postList);
        return "community";}

    @PostMapping("/{communityName}/delete")
    public String deleteCommunity(@PathVariable String communityName, Model model){
        boolean isDeleted = communityService.deleteCommunity(communityName);
        if(isDeleted){
        return "redirect:/";
    }else {return "redirect:/community/{communityName}";}
    }


    @PostMapping("/{communityName}")
    public String subscribeToCommunity(@PathVariable String communityName) {
        communityService.subscribeToCommunity(communityName);
        return "redirect:/community/{communityName}";
    }

    @PostMapping("/{communityName}/unsubscribe")
    public String unsubscribeFromCommunity(@PathVariable String communityName) {
        communityService.unsubscribeFromCommunity(communityName);
        return "redirect:/community/{communityName}";
    }

    @GetMapping("/communityCreation")
    public String showCommunityCreationForm() {return "communityCreation";}

    @PostMapping("/communityCreation")
    public String createCommunity(@ModelAttribute("community") CommunityDto communityDto){
        communityService.createCommunity(communityDto);
        return "redirect:/community/communityCreation?success";
    }




}
