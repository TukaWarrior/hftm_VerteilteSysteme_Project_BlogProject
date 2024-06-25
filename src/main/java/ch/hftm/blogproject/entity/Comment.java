// This class is currently unused. It serves as a WIP idea t oimplement comments under a blog post.
package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull @NotBlank @Size(min = 5, message = "Comment needs at least 5 characters")
    private String comment = "";
    private ZonedDateTime createdAt = ZonedDateTime.now();


    public Long getId() {
        return this.id;
    }

    public String getComment() {
        return this.comment;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
