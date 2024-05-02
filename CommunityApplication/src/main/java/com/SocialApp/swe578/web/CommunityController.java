package com.SocialApp.swe578.web;

import com.SocialApp.swe578.dto.CommunityDto;
import com.SocialApp.swe578.service.CommunityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {

    private CommunityService communityService;

    public CommunityController(CommunityService communityService){this.communityService = communityService;}
    @ModelAttribute("community")
    public CommunityDto communityDto(){return new CommunityDto();}

    @GetMapping("/{communityName}")
    public  String displayCommunityByName(@PathVariable String communityName,Model model){
            List<String> usernames =  communityService.listSubscribers(communityName);
            model.addAttribute("usernames", usernames);
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
