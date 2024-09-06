// package ch.hftm.blogproject.control;

// import ch.hftm.blogproject.boundary.dto.BlogPostDTO;
// import ch.hftm.blogproject.boundary.dto.CommentDTO;
// import ch.hftm.blogproject.entity.Account;
// import ch.hftm.blogproject.entity.BlogPost;
// import ch.hftm.blogproject.entity.Comment;
// import ch.hftm.blogproject.repository.AccountRepository;
// import ch.hftm.blogproject.repository.BlogPostRepository;
// import ch.hftm.blogproject.repository.CommentRepository;
// import io.quarkus.logging.Log;
// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import jakarta.transaction.Transactional;
// import java.time.ZonedDateTime;
// import java.util.List;
// import java.util.stream.Collectors;

// @ApplicationScoped
// public class CommentService {

//     @Inject
//     private CommentRepository commentRepository;
//     @Inject
//     private BlogPostRepository blogPostRepository;
//     @Inject
//     private AccountRepository accountRepository;


//     public List<Comment> getAllComments() {
//         return commentRepository.listAll();
//     }

//     public List<CommentDTO> getCommentsByBlog(Long blogPostId) {
//         try {
//             BlogPost blogPost = blogPostRepository.findById(blogPostId);
//             if (blogPost != null) {
//                 List<Comment> comments = blogPost.getComments();
//                 if (comments != null) {
//                     System.out.println(comments);
//                     return comments.stream()
//                             .map(CommentDTO::new)
//                             .collect(Collectors.toList());
//                 } else {
//                     return List.of();
//                 }
//             } else {
//                 throw new RuntimeException("Blog post not found");
//             }
//         } catch (Exception e) {
//             throw new RuntimeException("An error occurred while fetching comments", e);
//         }
//     }


//     @Transactional
//     public void addComment(CommentDTO commentDTO, Long blogPostId, Long accountId) {
//         if (blogPostId == null || accountId == null) {
//             throw new IllegalArgumentException("BlogPost ID and Account ID must not be null");
//         }
    
//         BlogPost blogPost = blogPostRepository.findById(blogPostId);
//         Account account = accountRepository.findById(accountId); // Check if account exists. 
    
//         if (blogPost == null || account == null) {
//             throw new IllegalArgumentException("BlogPost or Account not found");
//         }
    
//         try {
//             Comment comment = commentDTO.toEntity(blogPost, accountId);
//             comment.setBlogPost(blogPost);  // Set the relationship to the BlogPost
//             comment.setCreatedAt(ZonedDateTime.now());
    
//             commentRepository.persist(comment);
    
//             // Maintain the bidirectional relationship
//             blogPost.getComments().add(comment);
    
//             // Persist the blogPost to update the comments list in the database
//             blogPostRepository.persist(blogPost);
    
//         } catch (Exception e) {
//             throw new RuntimeException("An error occurred while adding the comment", e);
//         }
//     }

//     // @Transactional
//     // public void updateComment(Long id, CommentDTO updatedCommentDTO) {
//     //     Comment existingComment = commentRepository.findById(id);
//     //     if (existingComment != null) {
//     //         if (updatedCommentDTO.getContent() != null) {
//     //             existingComment.setContent(updatedCommentDTO.getContent());
//     //         }
//     //         existingComment.setChangedAt(ZonedDateTime.now());
//     //         commentRepository.persist(existingComment);
//     //         Log.info("Updated comment: " + existingComment.getId());
//     //     }
//     // }

//     // // Deletes one Comment by id.
//     // @Transactional
//     // public void deleteComment(Long id) {
//     //     Comment comment = commentRepository.findById(id);
//     //     if (comment != null) {
//     //         commentRepository.delete(comment);
//     //     }
//     // }


// }


// // Old Code
//     // Returns a list of comments for the passed blog id.
//     // public List<Comment> getComments(Long blogId, Optional<Long> pageNumber) {
//     //     Log.info("Searching comments for blog: " + blogId);
//     //     // return commentRepository.findByBlogId(blogId);
//     //     PanacheQuery<Comment> commentQuery;
//     //     commentQuery = commentRepository.find("blogId", blogId);
//     //     long pageIndex = pageNumber.orElse(1L);
//     //     List<Comment> comments = commentQuery.page(Page.of((int) (pageIndex - 1), pageSize)).list();
//     //     Log.info("Returning " + comments.size() + " comments");
//     //     return comments;
//     // }
