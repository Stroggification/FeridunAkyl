package com.SocialApp.swe578.dto;

public class PostDto {

    private String title;
    private String description;

    private String postCommunity;

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
