package ch.hftm.blogproject.control;

import ch.hftm.blogproject.entity.Comment;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CommentService {

    int pageSize = 10; // Settings: Defines the numbers of entries returned per page.

    @Inject
    private CommentRepository commentRepository;

    // Returns a list of comments for the passed blog id.
    public List<Comment> getComments(Long blogId, Optional<Long> pageNumber) {
        Log.info("Searching comments for blog: " + blogId);
        // return commentRepository.findByBlogId(blogId);
        PanacheQuery<Comment> commentQuery;
        commentQuery = commentRepository.find("blogId", blogId);
        long pageIndex = pageNumber.orElse(1L);
        List<Comment> comments = commentQuery.page(Page.of((int) (pageIndex - 1), pageSize)).list();
        Log.info("Returning " + comments.size() + " comments");
        return comments;
    }

    // Creates a comment with blogId reference.
    @Transactional
    public Comment pushComment(Long blogId, Comment comment) {
        comment.setBlogId(blogId);
        
        commentRepository.persist (comment);
        Log.info("Adding comment to blog: " + comment.getContent() + " " + comment.getId() + " " + comment.getBlogId());
        return comment;
    }
}
