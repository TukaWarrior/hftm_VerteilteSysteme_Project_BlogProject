package ch.hftm.blogproject.boundary;

import java.net.URI;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("blogs")
public class BlogRessource {

    @Inject
    BlogService blogService;
    

    @GET
    public Response getBlogs(@QueryParam("searchString") Optional<String> searchString, @QueryParam("page") Optional<Long> pageIndex) {
        // Log.info("Search for: " + serachString);
        return Response.status(Status.OK).entity(blogService.getBlogs(searchString, pageIndex)).build();
    }

    // @GET
    // public List<Blog> getBlogs(@QueryParam("serachString") Optional<String> search, @QueryParam("page") Optional<Long> page) {
    //     Log.info("Search for: " + search);
    //     return blogService.getBlogs(search, page);
    // }

    @GET
    @Path("{id}")
    public Response getBlog (long id) {
        return Response.status(Status.OK).entity(blogService.getBlogById(id)).build();
    }

    // @GET
    // @Path("{id}")
    // public Response getBlog (long id) {
    //     Blog blog = blogService.getBlog(id).orElseThrow(() -> new NotFoundException());
    //     return Response.status(Status.CREATED).entity(blog).header("info", "PAUSE!!").build();
    // }


    @POST
    public Response addBlog(Blog blog, @Context UriInfo uriInfo) {
        blogService.addBlog(blog);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(blog.getId())).build();
        return Response.created(uri).entity(blog).build();
    }
}
