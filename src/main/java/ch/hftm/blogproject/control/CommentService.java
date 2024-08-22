package ch.hftm.blogproject.control;

import ch.hftm.blogproject.boundary.dto.CommentDTO;
import ch.hftm.blogproject.entity.Account;
import ch.hftm.blogproject.entity.BlogPost;
import ch.hftm.blogproject.entity.Comment;
import ch.hftm.blogproject.repository.AccountRepository;
import ch.hftm.blogproject.repository.BlogPostRepository;
import ch.hftm.blogproject.repository.CommentRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
public class CommentService {

    int pageSize = 10; // Settings: Defines the numbers of entries returned per page.

    @Inject
    private CommentRepository commentRepository;
    @Inject
    private BlogPostRepository blogPostRepository;
    @Inject
    private AccountRepository accountRepository;


    public List<Comment> getAllComments() {
        return commentRepository.listAll();
    }

    public List<Comment> getCommentsByBlog(Long blogPostId) {
        BlogPost blogPost = blogPostRepository.findById(blogPostId);
        if (blogPost != null) {
            return blogPost.getComments();
        }
        return null;
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id);
    }


    @Transactional
    public Comment addComment(CommentDTO  commentDTO, Long blogPostId, Long accountId) {
        BlogPost blogPost = blogPostRepository.findById(blogPostId);
        Account account = accountRepository.findById(accountId);
        if (blogPost == null || account == null) {
            throw new IllegalArgumentException("BlogPost or Account not found");
        }
        Comment comment = commentDTO.toEntity(account);
        comment.setBlogPost(blogPost);
        comment.setBlogPost(blogPost); // For relations
        comment.setAccount(account);
        comment.setCreatedAt(ZonedDateTime.now());

        commentRepository.persist(comment);
        // blogPost.getComments().add(comment);
        // blogPostRepository.persist(blogPost);
        return comment;
    }

    @Transactional
    public void updateComment(Long id, CommentDTO updatedCommentDTO) {
        Comment existingComment = commentRepository.findById(id);
        if (existingComment != null) {
            if (updatedCommentDTO.getContent() != null) {
                existingComment.setContent(updatedCommentDTO.getContent());
            }
            existingComment.setChangedAt(ZonedDateTime.now());
            commentRepository.persist(existingComment);
            Log.info("Updated comment: " + existingComment.getId());
        }
    }

    // Deletes one Comment by id.
    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id);
        if (comment != null) {
            commentRepository.delete(comment);
        }
    }


}


// Old Code
    // Returns a list of comments for the passed blog id.
    // public List<Comment> getComments(Long blogId, Optional<Long> pageNumber) {
    //     Log.info("Searching comments for blog: " + blogId);
    //     // return commentRepository.findByBlogId(blogId);
    //     PanacheQuery<Comment> commentQuery;
    //     commentQuery = commentRepository.find("blogId", blogId);
    //     long pageIndex = pageNumber.orElse(1L);
    //     List<Comment> comments = commentQuery.page(Page.of((int) (pageIndex - 1), pageSize)).list();
    //     Log.info("Returning " + comments.size() + " comments");
    //     return comments;
    // }
