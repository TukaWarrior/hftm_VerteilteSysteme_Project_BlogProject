package ch.hftm.blogproject.control;

import ch.hftm.blogproject.model.dto.CommentDTO;
import ch.hftm.blogproject.model.entity.Comment;
import ch.hftm.blogproject.repository.AccountRepository;
import ch.hftm.blogproject.repository.BlogPostRepository;
import ch.hftm.blogproject.repository.CommentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommentService {

    @Inject
    CommentRepository commentRepository;
    
    @Inject
    BlogPostRepository blogPostRepository;
    
    @Inject
    AccountRepository accountRepository;

    // Get comments by blog post ID
    public List<CommentDTO> getCommentsByBlog(Long blogPostId) {
        // Directly query the CommentRepository for comments by blog post ID
        List<Comment> comments = commentRepository.find("blogPost.id", blogPostId).list();
        if (comments.isEmpty()) {
            throw new IllegalArgumentException("No comments found for the given blog post ID");
        }
        return comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    // Add a new comment to a blog post
    @Transactional
    public CommentDTO addComment(CommentDTO commentDTO, Long blogPostId, Long accountId) {
        if (blogPostRepository.findById(blogPostId) == null) {
            throw new IllegalArgumentException("BlogPost not found");
        }

        if (accountRepository.findById(accountId) == null) {
            throw new IllegalArgumentException("Account not found");
        }

        Comment comment = commentDTO.toEntity();
        comment.setBlogPost(blogPostRepository.findById(blogPostId));
        comment.setAccountId(accountId);

        commentRepository.persist(comment);
        return new CommentDTO(comment);
    }

    // Update an existing comment
    @Transactional
    public CommentDTO updateComment(Long id, CommentDTO updatedCommentDTO) {
        Comment existingComment = commentRepository.findById(id);
        if (existingComment == null) {
            throw new IllegalArgumentException("Comment not found for id: " + id);
        }
        existingComment.setContent(updatedCommentDTO.getContent());
        existingComment.setLastChangedAt(ZonedDateTime.now());

        commentRepository.persist(existingComment);
        return new CommentDTO(existingComment);
    }

    // Delete a comment by ID
    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id);
        if (comment == null) {
            throw new IllegalArgumentException("Comment not found for id: " + id);
        }
        commentRepository.delete(comment);
    }
}
