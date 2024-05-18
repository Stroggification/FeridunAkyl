package com.SocialApp.swe578.web;

import com.SocialApp.swe578.dto.PostDto;
import com.SocialApp.swe578.dto.TemplateDto;
import com.SocialApp.swe578.model.Community;
import com.SocialApp.swe578.model.Post;
import com.SocialApp.swe578.model.Template;
import com.SocialApp.swe578.repository.PostRepository;
import com.SocialApp.swe578.repository.TemplateRepository;
import com.SocialApp.swe578.service.PostService;
import com.SocialApp.swe578.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class PostController {
    private PostService postService;
    private PostRepository postRepository;

    private TemplateService templateService;
    private TemplateRepository templateRepository;

    @Autowired
    public PostController(PostRepository postRepository, PostService postService, TemplateService templateService, TemplateRepository templateRepository) {
        this.postRepository = postRepository;
        this.postService = postService;
        this.templateService = templateService;
        this.templateRepository = templateRepository;
    }


    @ModelAttribute("post")
    public PostDto postDto() {
        return new PostDto();
    }

    @ModelAttribute("template")
    public TemplateDto templateDto() {
        return new TemplateDto();
    }

    @GetMapping("{communityName}/postCreation")
    public String showPostCreationForm(@PathVariable String communityName, Model model) {
        List<Template> templates = templateService.listAllTemplatesInCommunity(communityName);
        model.addAttribute("templates", templates);
        return "postCreation";
    }

    @GetMapping("{communityName}/templates/{templateId}")
    public String showPostCreationFormWithTemplate(@PathVariable String communityName, @PathVariable String templateId, Model model) {
        Long id = Long.parseLong(templateId);
        Optional<Template> templateOptional = templateRepository.findById(id);
        Template template = templateOptional.orElse(null);
        model.addAttribute("template", template);
        return "postCreationWithTemplate";
    }


    @PostMapping("{communityName}/postCreation")
    public String createPost(@PathVariable String communityName, @ModelAttribute("post") PostDto postDto) {
        postDto.setPostCommunity(communityName);
        postService.createPost(postDto);
        return "redirect:/community/{communityName}?success";
    }

    @GetMapping("{communityName}/templateCreation")
    public String showTemplateCreationForm(@PathVariable String communityName) {
        return "templateCreation";
    }

    @PostMapping("{communityName}/templateCreation")
    public String createTemplate(@PathVariable String communityName, @ModelAttribute("template") TemplateDto templateDto) {
        templateDto.setTemplateCommunity(communityName);
        templateService.createTemplate(templateDto);
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
    public String deletePost(@PathVariable String postId, @PathVariable String communityName) {
        Long id = Long.parseLong(postId);
        boolean isDeleted = postService.deletePost(id);
        if (isDeleted) {
            return "redirect:/community/{communityName}";
        } else {
            return "redirect:/{communityName}/{postId}";
        }
    }

}
