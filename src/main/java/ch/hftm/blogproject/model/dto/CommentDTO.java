package ch.hftm.blogproject.model.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ch.hftm.blogproject.model.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during deserialization
public class CommentDTO {
    private Long commentID;  // Present in GET responses, null for POST
    private Long blogPostID;
    @NotBlank(message = "Content cannot be empty")
    private String content;
    private String creator;
    private ZonedDateTime createdAt;  // Present in GET responses, not used in POST
    private ZonedDateTime lastChangedAt;  // Present in GET responses, not used in POST

    // Constructor for GET responses
    // public CommentDTO(Comment comment) {
    //     this.commentID = comment.getCommentID();
    //     this.content = comment.getContent();
    //     this.createdAt = comment.getCreatedAt();
    //     this.lastChangedAt = comment.getLastChangedAt();
    //     this.accountId = comment.getAccountId();
    //     this.blogPostId = comment.getBlogPost().getBlogPostID();
    // }

    // Convert from DTO to entity
    // public Comment toEntity() {
    //     Comment comment = new Comment();
    //     comment.setContent(this.content);
    //     comment.setCreatedAt(ZonedDateTime.now());
    //     comment.setLastChangedAt(ZonedDateTime.now());
    //     return comment;
    // }
}
