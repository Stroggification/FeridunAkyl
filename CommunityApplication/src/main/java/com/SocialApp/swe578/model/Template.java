package com.SocialApp.swe578.model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "template")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_name")
    private String templateName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id", nullable = false)
    private Community templateCommunity;


    @ElementCollection
    @CollectionTable(name = "custom_template_fields", joinColumns = @JoinColumn(name = "template_id"))
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    private Map<String, String> customFields = new HashMap<>();

    public Template(String templateName, Community templateCommunity, Map<String, String> customFields) {
        this.templateName = templateName;
        this.templateCommunity = templateCommunity;
        this.customFields = customFields;
    }

    public Template() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Community getTemplateCommunity() {
        return templateCommunity;
    }

    public void setTemplateCommunity(Community templateCommunity) {
        this.templateCommunity = templateCommunity;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }
}
