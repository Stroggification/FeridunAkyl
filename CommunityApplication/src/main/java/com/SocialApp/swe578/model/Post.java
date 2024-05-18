package com.SocialApp.swe578.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;
    @Column(nullable = false)
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id", nullable = false)
    private Community postCommunity;

    @ElementCollection
    @CollectionTable(name = "post_custom_fields", joinColumns = @JoinColumn(name = "post_id"))
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    private Map<String, String> customFields = new HashMap<>();


    public Post() {
    }

    public Post(String title, String description, Date date, User creator, Community postCommunity) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.creator = creator;
        this.postCommunity = postCommunity;
    }

    public Post(Date date, User creator, Community postCommunity, Map<String, String> customFields) {
        this.date = date;
        this.creator = creator;
        this.postCommunity = postCommunity;
        this.customFields = customFields;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Community getPostCommunity() {
        return postCommunity;
    }

    public void setPostCommunity(Community postCommunity) {
        this.postCommunity = postCommunity;
    }
}
