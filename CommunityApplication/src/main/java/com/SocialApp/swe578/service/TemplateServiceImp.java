package com.SocialApp.swe578.service;

import com.SocialApp.swe578.dto.PostDto;
import com.SocialApp.swe578.dto.TemplateDto;
import com.SocialApp.swe578.model.Community;
import com.SocialApp.swe578.model.Template;
import com.SocialApp.swe578.repository.CommunityRepository;
import com.SocialApp.swe578.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImp implements TemplateService{
    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private TemplateRepository templateRepository;

    private UserServiceImpl userService;



    public TemplateServiceImp(CommunityRepository communityRepository, TemplateRepository templateRepository, UserServiceImpl userService) {
        this.communityRepository = communityRepository;
        this.templateRepository = templateRepository;
        this.userService = userService;
    }
    @Override
    public Template createTemplate(TemplateDto templateDto) {


        Community templateCommunity = communityRepository.findByName(templateDto.getTemplateCommunity());
        if(templateCommunity.getOwner().equals(userService.getAuthUser())) {
            Template newTemplate = new Template(templateDto.getTemplateName(), templateCommunity, templateDto.getCustomFields());

            return templateRepository.save(newTemplate);
        }else {
            throw new AccessDeniedException("You do not have permission to create a template for this community. You Must Be The Owner");
        }
        }

    @Override
    public List<Template> listAllTemplatesInCommunity(String communityName) {
        Community templateCommunity = communityRepository.findByName(communityName);
        return templateCommunity.getTemplates();
    }
}

