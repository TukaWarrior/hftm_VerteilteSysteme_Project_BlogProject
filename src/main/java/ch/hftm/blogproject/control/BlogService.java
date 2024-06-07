package ch.hftm.blogproject.control;

import java.util.List;
import java.util.Optional;

import ch.hftm.blogproject.entity.Blog;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BlogService {
    @Inject
    private BlogRepository blogRepository;

    public List<Blog> getBlogs() {
        return this.getBlogs(Optional.empty(), Optional.empty());
    }

    public List<Blog> getBlogs(Optional<String> searchString, Optional<Long> page) {
        PanacheQuery<Blog> blogQuery;
        if (searchString.isEmpty()) {
            blogQuery = blogRepository.findAll();
        } else {
            blogQuery = blogRepository.find("title like ?1 or content like ?1", "%" + searchString.get() +"%");
        }
        var blogs = blogQuery.page(Page.ofSize(2)).list();
        Log.info("Returning " + blogs.size() + " blogs");
        return blogs;
    }

    public Optional<Blog> getBlog(long id) {
        return blogRepository.findByIdOptional(id);
    }

    @Transactional
    public void addBlog(Blog blog) {
        Log.info("Adding blog " + blog.getTitle());
        blogRepository.addBlog(blog);
    }
}