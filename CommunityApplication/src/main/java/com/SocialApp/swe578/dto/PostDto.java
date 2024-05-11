package com.SocialApp.swe578.dto;

import java.util.Map;

public class PostDto {

    private String title;
    private String description;

    private String postCommunity;
    //Hashmap will be used to create dynamic fields for the post template its just an idea for now
    private Map<String, String> customFields;

    public PostDto(){}

    public PostDto(String title, String description, String postCommunity) {
        this.title = title;
        this.description = description;
        this.postCommunity = postCommunity;
    }

    public String getPostCommunity() {
        return postCommunity;
    }

    public void setPostCommunity(String postCommunity) {
        this.postCommunity = postCommunity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
