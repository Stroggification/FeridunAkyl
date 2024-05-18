package com.SocialApp.swe578.dto;

import com.SocialApp.swe578.model.Template;

import java.util.HashMap;
import java.util.Map;

public class TemplateDto {
    private String templateName;

    private String templateCommunity;
    private Map<String, String> customFields = new HashMap<>();

    public TemplateDto() {
    }

    public TemplateDto(String templateName, String templateCommunity, Map<String, String> customFields) {
        this.templateName = templateName;
        this.templateCommunity = templateCommunity;
        this.customFields = customFields;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateCommunity() {
        return templateCommunity;
    }

    public void setTemplateCommunity(String templateCommunity) {
        this.templateCommunity = templateCommunity;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }
}
