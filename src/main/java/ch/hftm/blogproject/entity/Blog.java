package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Defines the Blog entity representing a blog post with its properties

@Entity
public class Blog {

    @Id 
    @GeneratedValue
    private Long id;
    @NotNull @Size(min = 5, message = "Title needs at least 5 characters")
    private String title = "";
    @NotBlank
    private String content= "";

    // private long likes;

    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime lastChangedAt = ZonedDateTime.now();
    
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }


    public String getContent() {
        return this.content;
    }

    // public long getLikes() {
    //     return this.likes;
    // }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public ZonedDateTime getLastChangedAt() {
        return this.lastChangedAt;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }

    // public void setLikes(long likes) {
    //     this.likes = likes;
    // }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastChangesAt() {
        this.lastChangedAt = ZonedDateTime.now();
    }

    public Blog() {
    }

    // Constructor used in StartupBean Class to initialize some blogs for easier testing. 
    public Blog(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
