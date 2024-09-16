package ch.hftm.blogproject.boundary.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ch.hftm.blogproject.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during deserialization
public class CommentDTO {

    private Long id;  // Present in GET responses, null for POST

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private ZonedDateTime createdAt;  // Present in GET responses, not used in POST
    private ZonedDateTime changedAt;  // Present in GET responses, not used in POST

    private Long accountId;  // For POST requests, to associate the comment with an account
    private Long blogPostId;  // For POST requests, to associate the comment with a blog post

    // Constructor for GET responses
    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.changedAt = comment.getChangedAt();
        this.accountId = comment.getAccountId();
        this.blogPostId = comment.getBlogPost().getId();
    }

    // Convert from DTO to entity
    public Comment toEntity() {
        Comment comment = new Comment();
        comment.setContent(this.content);
        comment.setCreatedAt(ZonedDateTime.now());
        comment.setChangedAt(ZonedDateTime.now());
        return comment;
    }
}
