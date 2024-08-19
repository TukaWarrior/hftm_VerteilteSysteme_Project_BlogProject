package ch.hftm.blogproject.control;

import ch.hftm.blogproject.entity.Blog;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

// This class serves as the interface between the BlogService class and the MySQL Database.

@ApplicationScoped
public class BlogRepository implements PanacheRepository<Blog>{
}
