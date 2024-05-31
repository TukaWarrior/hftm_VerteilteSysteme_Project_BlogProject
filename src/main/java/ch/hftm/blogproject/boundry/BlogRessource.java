package ch.hftm.blogproject.boundry;

import java.util.List;

import ch.hftm.blogproject.control.BlogService;
import ch.hftm.blogproject.entity.Blog;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

public class BlogRessource {

    @Inject
    BlogService blogService;
    
    @GET
    public List<Blog> getBlogs() {
        return blogService.getBlogs();
    }

    // @GET
    // @Path("{id}")
    // public Blog getBlog(long id) {
    //     return blogService.getBlog(id);
    // }

    @POST
    public void addBlog(Blog b) {
        blogService.addBlog(b);
    }
}
