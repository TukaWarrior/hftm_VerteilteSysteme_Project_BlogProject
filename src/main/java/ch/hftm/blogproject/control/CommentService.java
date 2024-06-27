package ch.hftm.blogproject.control;

import ch.hftm.blogproject.entity.Blog;
import ch.hftm.blogproject.entity.Comment;
import ch.hftm.blogproject.control.BlogService;
import io.quarkus.logging.Log;
import ch.hftm.blogproject.control.CommentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CommentService {

    int pageSize = 10; // Settings: Defines the numbers of entries returned per page.

    @Inject
    private CommentRepository commentRepository;

    // @Inject
    // private BlogService blogService;

    // Returns a list of comments for the passed blog id.
    public List<Comment> getComments(long blogId) {
        // Blog blog = blogService.getBlogById(blogId);
        return commentRepository.findByBlogId(blogId);
    }

    // Doesen't work: Creates a new blog with a new id.
    @Transactional
    public Comment pushComment(Comment comment) {
        Log.info("Adding comment to blog");
        commentRepository.persist (comment);
        return comment;
    }
}
