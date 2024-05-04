package com.SocialApp.swe578.web;

import com.SocialApp.swe578.dto.PostDto;
import com.SocialApp.swe578.model.Community;
import com.SocialApp.swe578.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private PostService postService;


    public PostController(PostService postService){this.postService=postService;}

    @ModelAttribute("post")
    public PostDto postDto() {
        return new PostDto();
    }

    @GetMapping("{communityName}/postCreation")
    public String showPostCreationForm(@PathVariable String communityName){
        return "postCreation";}

    @PostMapping("{communityName}/postCreation")
    public String createPost(@PathVariable String communityName,@ModelAttribute("post") PostDto postDto){
        postDto.setPostCommunity(communityName);
        postService.createPost(postDto);
        return "redirect:/{communityName}/postCreation?success";
    }


}
