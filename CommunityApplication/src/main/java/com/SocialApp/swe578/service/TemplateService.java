package com.SocialApp.swe578.service;

import com.SocialApp.swe578.dto.PostDto;
import com.SocialApp.swe578.dto.TemplateDto;
import com.SocialApp.swe578.model.Template;

import java.util.List;

public interface TemplateService {

    Template createTemplate(TemplateDto templateDto);

    List<Template> listAllTemplatesInCommunity(String communityName);
}
