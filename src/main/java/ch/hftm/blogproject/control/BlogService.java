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


// This class handles the business logic for the blog posts. It interacts with the BlogRessource class and the BlogRepository class.

@ApplicationScoped
public class BlogService {

    int pageSize = 4; // Settings: Defines the numbers of entries returned per page.

    @Inject
    private BlogRepository blogRepository;

    // Returns a list of blogs that match the passed search terms. 
    public List<Blog> getBlogs(Optional<String> searchString, Optional<Long> pageNumber) {
        PanacheQuery<Blog> blogQuery;
        if (searchString.isEmpty()) {
            blogQuery = blogRepository.findAll();
        } else {
            blogQuery = blogRepository.find("title like ?1 or content like ?1", "%" + searchString.get() +"%");
        }
    
        long pageIndex = pageNumber.orElse(1L);
        // List<Blog> blogs = blogQuery.page(Page.ofSize(2)).list(); // Old code. Just define the page size. For automatic scrolling in frontend? 
        List<Blog> blogs = blogQuery.page(Page.of((int) (pageIndex - 1), pageSize)).list();
        Log.info("Returning " + blogs.size() + " blogs");
        return blogs;
    }

    // Returns a blog with the passed id.
    public Blog getBlogById(long id) {
        return blogRepository.findById(id);
    }

    // Creates a new blog with a new id.
    @Transactional
    public Blog pushBlog(Blog blog) {
        Log.info("Adding blog " + blog.getTitle());
        blogRepository.persist(blog);
        return blog;
    }

    // Deletes an existing blog with a matching id.
    @Transactional
    public Blog deleteBlog(long id) {
        Blog blog = blogRepository.findById(id);
        if (blog != null) {
            blogRepository.delete(blog);
            Log.info("Deleted blog with id " + id);
            return blog;
        } else {
            Log.warn("Blog with id " + id + " not found for deletion");
            return null;
        }
    }

    // Replaces attributes of an existing blog with a matching id.
    @Transactional
    public Blog putBlog(Long id, Blog newBlog) {
        Blog existingBlog = blogRepository.findById(id);
        if (existingBlog != null) {
            // I treid to replace the existing blog entity completely but it would just create a new blog. SOme problems with the Primary key. 
            // For now, I just replace each attribute manualy, which seems to be suboptimal.
            existingBlog.setTitle(newBlog.getTitle());
            existingBlog.setContent(newBlog.getContent());
            existingBlog.setEditedAt();
            blogRepository.persist(existingBlog);
            Log.info("Replaced blog with id " + id);
            return existingBlog;
        } else {
            Log.warn("Blog with id " + id + " not found for replacement");
            return null;
        }
    }

    // Replaces attributes of existing blog with a matching id only when they are not empty.
    @Transactional
    public Blog patchBlog(long id, Blog newBlog) {
        Blog existingBlog = blogRepository.findById(id);
        if (existingBlog != null) {
            if (newBlog.getTitle().isEmpty() != true) {
                existingBlog.setTitle(newBlog.getTitle());
            }
            if (newBlog.getContent().isEmpty() != true) {
                existingBlog.setContent(newBlog.getContent());
            }
            existingBlog.setEditedAt();
            blogRepository.persist(existingBlog);
            Log.info("Partially updated blog with id " + id);
            return existingBlog;
        } else {
            Log.warn("Blog with id " + id + " not found for partial update");
        }
        return null;
    }
}