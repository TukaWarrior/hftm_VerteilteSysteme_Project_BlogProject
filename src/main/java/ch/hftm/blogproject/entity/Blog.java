package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Defines the Blog entity representing a blog post with its properties

@Entity
public class Blog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull @NotBlank @Size(min = 5, message = "Title needs at least 5 characters")
    private String title = "";
    @NotNull @NotBlank
    private String content= "";
    private long likes;
    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime changedAt = ZonedDateTime.now();
    
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

    public ZonedDateTime getChangedAt() {
        return this.changedAt;
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
        this.changedAt = ZonedDateTime.now();
    }

    // Constructors
    public Blog() {
    }

    // Constructor used in Blog DTO
    public Blog (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
