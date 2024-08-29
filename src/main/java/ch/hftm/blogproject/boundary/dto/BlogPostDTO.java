package ch.hftm.blogproject.boundary.dto;

import java.time.ZonedDateTime;
import ch.hftm.blogproject.entity.Account;
import ch.hftm.blogproject.entity.BlogPost;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDTO {
    private Long id; // Present in GET responses, null for POST
    @NotBlank(message = "Title can not be empty")
    private String title;
    @NotBlank(message = "Content can not be empty")
    private String content;
    private ZonedDateTime createdAt;  // Present in GET responses, not used in POST
    private ZonedDateTime changedAt;  // Present in GET responses, not used in POST
    private Long accountId;  // For POST requests, to associate the blog post with an account
    private String accountName;  // For GET requests, to display the account's name

    // Constructor for testing
    public BlogPostDTO  (String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Constructor for GET responses
    public BlogPostDTO(BlogPost blogPost) {
        this.id = blogPost.getId();
        this.title = blogPost.getTitle();
        this.content = blogPost.getContent();
        this.createdAt = blogPost.getCreatedAt();
        this.changedAt = blogPost.getChangedAt();
        this.accountId = blogPost.getAccountId();
        // this.accountName = blogPost.getAccount() != null ? blogPost.getAccount().getName() : null;
    }


    // Method to convert from DTO to entity for POST requests
    public BlogPost toEntity(Long accountId) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(this.title);
        blogPost.setContent(this.content);
        blogPost.setCreatedAt(ZonedDateTime.now());
        return blogPost;
    }


}