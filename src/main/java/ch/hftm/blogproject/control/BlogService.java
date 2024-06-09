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
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

// This class handles the business logic for the blog posts. It interacts with the BlogRessource class and the BlogRepository class.

@ApplicationScoped
public class BlogService {

    int pageSize = 4; // Settings: Defines the numbers of entries returned per page.

    @Inject
    private BlogRepository blogRepository;

    public List<Blog> getBlogs() {
        return this.getBlogs(Optional.empty(), Optional.empty());
    }

    public List<Blog> getBlogs(Optional<String> searchString, Optional<Long> pageNumber) {
        PanacheQuery<Blog> blogQuery;
        if (searchString.isEmpty()) {
            blogQuery = blogRepository.findAll();
        } else {
            blogQuery = blogRepository.find("title like ?1 or content like ?1", "%" + searchString.get() +"%");
        }

        long pageIndex = pageNumber.orElse(1L);

        // List<Blog> blogs = blogQuery.page(Page.ofSize(2)).list(); // Old code. Just define the page size. For automatic scrolling in frontend. 
        List<Blog> blogs = blogQuery.page(Page.of((int) (pageIndex - 1), pageSize)).list();
        Log.info("Returning " + blogs.size() + " blogs");
        return blogs;
    }

    public Blog getBlogById(long id) {
        return blogRepository.findById(id);
    }

    @Transactional
    public void addBlog(Blog blog) {
        Log.info("Adding blog " + blog.getTitle());
        blogRepository.persist(blog);
    }

    @Transactional
    public void deleteBlog(long id) {
        Blog blog = blogRepository.findById(id);
        if (blog != null) {
            blogRepository.delete(blog);
            Log.info("Deleted blog with id " + id);
        } else {
            Log.warn("Blog with id " + id + " not found for deletion");
        }
    }

    @Transactional
    public boolean replaceBlog(Long id, Blog blog) {
        Blog existingBlog = blogRepository.findById(id);
        if (existingBlog != null) {
            // I treid to replace the existing blog entity completely but it would just create a new blog. SOme problems with the Primary key. 
            // For now, I just replace each attribute manualy, which seems to be suboptimal.
            // Blog newBlog = new Blog(id, blog.getTitle(), blog.getContent(), existingBlog.getDateTime());
            // blogRepository.delete(existingBlog);
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.getContent());
            blogRepository.persist(existingBlog);
            Log.info("Replaced blog with id " + id);
            return true;
        } else {
            Log.warn("Blog with id " + id + " not found for replacement");
            return false;
        }
    }
}