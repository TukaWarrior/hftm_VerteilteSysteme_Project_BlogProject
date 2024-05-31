package ch.hftm.blogproject.control;

import java.util.List;

import ch.hftm.blogproject.entity.Blog;
import ch.hftm.blogproject.repository.BlogRepository;
import io.quarkus.logging.Log;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class BlogService {
    @Inject
    BlogRepository blogRepository;

    public List<Blog> getBlogs() {
        var blogs = blogRepository.getBlogs();
        Log.info("Returning " + blogs.size() + " blogs");
        return blogs;
    }

    // public void getBlog(long id) {
    //     return blog;
    // }

    public void addBlog(Blog blog) {
        Log.info("Adding blog " + blog.getTitle());
        blogRepository.addBlog(blog);
    }
}