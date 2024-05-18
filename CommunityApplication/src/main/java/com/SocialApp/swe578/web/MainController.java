package com.SocialApp.swe578.web;

import com.SocialApp.swe578.model.Community;
import com.SocialApp.swe578.repository.CommunityRepository;
import com.SocialApp.swe578.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    private CommunityService communityService;

    private CommunityRepository communityRepository;
    public  MainController(CommunityService communityService, CommunityRepository communityRepository){
        this.communityService = communityService;
        this.communityRepository = communityRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/")
    public String listCommunities(Model model){
        model.addAttribute("communities", communityRepository.findAll());
        return "index";

    }
    @GetMapping("/search")
    public String searchCommunity(@RequestParam String communityName, RedirectAttributes redirectAttributes) {
        if (communityService.findCommunity(communityName)) {
            return "redirect:/community/" + communityName;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Community not found.");
            return "redirect:/";
        }

    }

}
