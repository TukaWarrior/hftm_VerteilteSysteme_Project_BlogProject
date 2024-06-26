package ch.hftm.blogproject.control;

import java.util.List;

import ch.hftm.blogproject.entity.Comment;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

// This class serves as the interface between the CommentService class and the MySQL Database.

@ApplicationScoped
public class CommentRepository implements PanacheRepository<Comment> {
    public List<Comment> findByBlogId(Long blogId) {
        return find("blog.id", blogId).list();
    }
}