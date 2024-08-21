package ch.hftm.blogproject.control;

import ch.hftm.blogproject.entity.Blog;
import ch.hftm.blogproject.entity.Comment;
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


    public List<Comment> getAllComments() {
        return commentRepository.listAll();
    }

    public List<Comment> getCommentsByBlog(Long blogPostId) {
        Blog blogPost = blogPostRepository.findById(blogPostId);
        if (blogPost != null) {
            return blogPost.getComments();
        }
        return null;
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id);
    }


    @Transactional
    public Comment addComment(String content, Long accountId, Long blogPostId) {
        Blog blogPost = blogPostRepository.findById(blogPostId);
        if (blogPost == null) {
            throw new IllegalArgumentException("BlogPost not found");
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAccountId(accountId);
        comment.setCreatedAt(ZonedDateTime.now());

        commentRepository.persist(comment);
        blogPost.getComments().add(comment);
        blogPostRepository.persist(blogPost);
        return comment;
    }

    @Transactional
    public void updateComment(Long id, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(id);
        if (existingComment != null) {
            if (updatedComment.getContent() != null) {
                existingComment.setContent(updatedComment.getContent());
            }
            existingComment.setChangedAt(ZonedDateTime.now());
            commentRepository.persist(existingComment);
            Log.info("Updated comment: " + existingComment.getId());
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
