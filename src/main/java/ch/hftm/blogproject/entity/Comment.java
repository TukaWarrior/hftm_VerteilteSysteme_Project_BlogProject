// This class is currently unused. It serves as a WIP idea t oimplement comments under a blog post.
package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 5, message = "Comment needs at least 5 characters")
    private String content = "";
    private Long likes;
    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime editedAt = ZonedDateTime.now();
    @ManyToOne
    private Blog blog;

    // Getters
    public Long getId() {
        return this.id;
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

    public Blog getBlog() {
        return blog;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setEditedAt() {
        this.editedAt = ZonedDateTime.now();
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    // Constructors
    public Comment() {
    }

    // Constructor used in StartupBean Class to initialize some comments for easier testing. 
    public Comment(Blog blog, String content) {
        this.blog = blog;
        this.content = content;
    }

    // Constructor used in Comment DTO
    public Comment (String content) {
        this.content = content;
    }
}
