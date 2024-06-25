package ch.hftm.blogproject.control;

import ch.hftm.blogproject.entity.Blog;
import ch.hftm.blogproject.entity.Comment;
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

    // Returns a list of comments for the passed blog id.
    public List<Comment> getComments(long blogId) {
        return commentRepository.findByBlogId(blogId);
    }

    // Creates a new blog with a new id.
    @Transactional
    public Comment pushComment(Long blogId, Comment comment) {
        Log.info("Adding comment to blog");
        commentRepository.persist(comment);
        return comment;
    }
}
