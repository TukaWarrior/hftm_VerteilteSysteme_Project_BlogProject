package ch.hftm.blogproject.boundry;

import java.util.List;
import java.util.Optional;

import ch.hftm.blogproject.control.BlogService;
import ch.hftm.blogproject.entity.Blog;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("blogs")
public class BlogRessource {

    @Inject
    BlogService blogService;
    
    @GET
    public List<Blog> getBlogs(@QueryParam("serachString") Optional<String> search, @QueryParam("page") Optional<Long> page) {
        Log.info("Search for: " + search);
        return blogService.getBlogs(search, page);
    }

    // Older code V1
    // @GET
    // @Path("{id}")
    // public Blog getBlog(long id) {
    //     return blogService.getBlog(id);
    // }

    // Olde code V2
    // @GET
    // @Path("{id}")
    // public Response getBlog (long id) {
    //     Blog blog = blogService.getBlog(id).orElse(throw new NotFoundException())
    //     return Response.status(Status.CREATED).entity(blogService.getBlog(id)).header("info", "PAUSE!!").build();
    // }

    @GET
    @Path("{id}")
    public Response getBlog (long id) {
        Blog blog = blogService.getBlog(id).orElseThrow(() -> new NotFoundException());
        return Response.status(Status.CREATED).entity(blog).header("info", "PAUSE!!").build();
    }

    @POST
    public void addBlog(Blog b) {
        blogService.addBlog(b);
    }
}
