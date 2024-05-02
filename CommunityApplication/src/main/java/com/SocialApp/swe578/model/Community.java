package com.SocialApp.swe578.model;

import jakarta.persistence.*;

import javax.management.Descriptor;
import java.util.List;
@Entity
@Table(name = "community", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn( name = "owner_id")
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "community_users",
            joinColumns = @JoinColumn(name = "community_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id")
    )
    private List<User> subscribers;

    public Community(){}
    public Community(String name, String description, User owner, List<User> subscribers) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.subscribers = subscribers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }
}
