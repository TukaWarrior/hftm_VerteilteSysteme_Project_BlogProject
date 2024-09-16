package ch.hftm.blogproject.model.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ch.hftm.blogproject.model.entity.BlogPost;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during deserialization

public class BlogPostDTO {

    private Long id; // Present in GET responses, null for POST

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private ZonedDateTime createdAt;
    private ZonedDateTime changedAt;
    private Long accountId;

    // Convert from BlogPost entity to BlogPostDTO
    public BlogPostDTO(BlogPost blogPost) {
        this.id = blogPost.getId();
        this.title = blogPost.getTitle();
        this.content = blogPost.getContent();
        this.createdAt = blogPost.getCreatedAt();
        this.changedAt = blogPost.getLastChangedAt();
        this.accountId = blogPost.getAccountId();
    }

    // Convert from DTO to BlogPost entity
    public BlogPost toEntity() {
        return new BlogPost(this.title, this.content, this.accountId);
    }
}
