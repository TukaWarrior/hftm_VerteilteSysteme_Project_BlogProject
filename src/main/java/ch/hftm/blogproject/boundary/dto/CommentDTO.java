// package ch.hftm.blogproject.boundary.dto;

// import java.time.ZonedDateTime;

// import ch.hftm.blogproject.entity.Account;
// import ch.hftm.blogproject.entity.Comment;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class CommentDTO {

//     private Long id;  // Present in GET responses, null for POST
//     private String content;
//     private ZonedDateTime createdAt;  // Present in GET responses, not used in POST
//     private ZonedDateTime changedAt;  // Present in GET responses, not used in POST
//     private Long accountId;  // For POST requests, to associate the comment with an account
//     private Long blogPostId;  // For POST requests, to associate the comment with a blog post

//     public CommentDTO(String content) {
//         this.content = content;
//     }

//     // Constructor for GET responses
//     public CommentDTO(Comment comment) {
//         this.id = comment.getId();
//         this.content = comment.getContent();
//         this.createdAt = comment.getCreatedAt();
//         this.changedAt = comment.getChangedAt();
//         this.accountId = comment.getAccount().getId();
//         this.blogPostId = comment.getBlogPost().getId();
//         // this.accountId = comment.getAccount() != null ? comment.getAccount().getId() : null;
//         // this.blogPostId = comment.getBlogPost() != null ? comment.getBlogPost().getId() : null;
//     }

//     // Method to convert from DTO to entity for POST requests
//     public Comment toEntity(Account account) {
//         Comment comment = new Comment();
//         comment.setContent(this.content);
//         comment.setCreatedAt(ZonedDateTime.now());
//         comment.setChangedAt(ZonedDateTime.now());
//         comment.setAccount(account);
//         return comment;
//     }
// }