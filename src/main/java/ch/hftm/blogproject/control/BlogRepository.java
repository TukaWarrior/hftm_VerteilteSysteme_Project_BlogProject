package ch.hftm.blogproject.control;

import ch.hftm.blogproject.entity.Blog;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlogRepository implements PanacheRepository<Blog>{
}