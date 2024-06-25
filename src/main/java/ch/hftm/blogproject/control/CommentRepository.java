package ch.hftm.blogproject.control;

import ch.hftm.blogproject.entity.Comment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CommentRepository implements PanacheRepository<Comment> {
    public List<Comment> findByBlogId(Long blogId) {
        return list("blog.id", blogId);
    }
}