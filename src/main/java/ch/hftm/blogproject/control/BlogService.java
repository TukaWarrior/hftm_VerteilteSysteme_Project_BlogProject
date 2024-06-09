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

    int pageSize = 2; // Defines the numbers of entries returned per page.

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
}