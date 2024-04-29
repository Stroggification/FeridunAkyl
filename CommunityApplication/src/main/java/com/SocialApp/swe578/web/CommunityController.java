package com.SocialApp.swe578.web;

import com.SocialApp.swe578.dto.CommunityDto;
import com.SocialApp.swe578.service.CommunityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/community")
public class CommunityController {

    private CommunityService communityService;

    public CommunityController(CommunityService communityService){this.communityService = communityService;}
    @ModelAttribute("community")
    public CommunityDto communityDto(){return new CommunityDto();}

    @GetMapping("/{communityName}")
    public  String getCommunityByName(@PathVariable String communityName){
        return "community";}



    @GetMapping("/communityCreation")
    public String showCommunityCreationForm() {return "communityCreation";}

    @PostMapping("/communityCreation")
    public String createCommunity(@ModelAttribute("community") CommunityDto communityDto){
        communityService.createCommunity(communityDto);
        return "redirect:/community/communityCreation?success";
    }



}
