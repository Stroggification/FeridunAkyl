package com.SocialApp.swe578.web;

import com.SocialApp.swe578.dto.PostDto;
import com.SocialApp.swe578.model.Community;
import com.SocialApp.swe578.model.Post;
import com.SocialApp.swe578.repository.PostRepository;
import com.SocialApp.swe578.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class PostController {
    private PostService postService;
    private PostRepository postRepository;
    @Autowired
    public PostController(PostRepository postRepository, PostService postService){
        this.postRepository = postRepository;
        this.postService = postService;}



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
        return "redirect:/community/{communityName}?success";
    }

    @GetMapping("{communityName}/{postId}")
    public String displayPost(@PathVariable String postId, @PathVariable String communityName, Model model) {
        Long id = Long.parseLong(postId);
        Optional<Post> postOptional = postRepository.findById(id);
        Post post = postOptional.orElse(null);
        model.addAttribute("post", post);
        return "post";
    }
    @PostMapping("{communityName}/{postId}/delete")
    public String deletePost(@PathVariable String postId, @PathVariable String communityName){
        Long id = Long.parseLong(postId);
        boolean isDeleted = postService.deletePost(id);
        if(isDeleted){
            return "redirect:/community/{communityName}";
        }
        else {return "redirect:/{communityName}/{postId}";}
    }

}
