package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Defines the Blog entity representing a blog post with its properties

@Entity
public class Blog {

    @Id 
    @GeneratedValue
    private long id;
    @NotNull
    @NotBlank
    @Size(min = 5, message = "Title needs at least 5 characters")
    private String title = "";
    @NotBlank
    private String content= "";
    private long likes;
    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime editedAt = ZonedDateTime.now();
    
    // Getters
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Long getLikes() {
        return this.likes;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public ZonedDateTime getEditedAt() {
        return this.editedAt;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setEditedAt() {
        this.editedAt = ZonedDateTime.now();
    }

    // Constructors
    public Blog() {
    }

    // Constructor used in StartupBean Class to initialize some blogs for easier testing. 
    // public Blog(Long id, String title, String content) {
    //     this.id = id;
    //     this.title = title;
    //     this.content = content;
    // }

    // Constructor used in Blog DTO
    public Blog (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
