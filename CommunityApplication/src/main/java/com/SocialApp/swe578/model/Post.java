package com.SocialApp.swe578.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
    @Column(nullable = false)
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id")
    private Community postCommunity;

    public Post() {
    }

    public Post(String title, String description, Date date, User creator, Community postCommunity) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.creator = creator;
        this.postCommunity = postCommunity;
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
