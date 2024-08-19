// This class is currently unused. It serves as a WIP idea t oimplement comments under a blog post.
package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;

import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Comment {

    @Id @GeneratedValue
    private long id;
    private long blogId;
    @NotNull @NotBlank @Size(min = 5, message = "Comment needs at least 5 characters")
    private String content = "";
    // private Long likes;
    // private ZonedDateTime createdAt = ZonedDateTime.now();
    // private ZonedDateTime editedAt = ZonedDateTime.now();

    // Foreign key reference to the blog
    // @ManyToOne
    // @JoinColumn(name = "blog_id", nullable = false)
    // private Blog blog;

    // Getters
    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    // public Long getLikes() {
    //     return this.likes;
    // }

    // public ZonedDateTime getCreatedAt() {
    //     return this.createdAt;
    // }

    // public ZonedDateTime getEditedAt() {
    //     return this.editedAt;
    // }

    public Long getBlogId() {
        return blogId;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // public void setLikes(Long likes) {
    //     this.likes = likes;
    // }

    // public void setCreatedAt(ZonedDateTime createdAt) {
    //     this.createdAt = createdAt;
    // }

    // public void setEditedAt() {
    //     this.editedAt = ZonedDateTime.now();
    // }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    // Constructors
    public Comment() {
    }

    // Constructor used in Comment DTO
    public Comment (String content) {
        this.content = content;
    }

    public Comment (long blogId, String content) {
        this.blogId = blogId;
        this.content = content;
    }
}