package ch.hftm.blogproject.boundary;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.hftm.blogproject.control.BlogPostService;
import ch.hftm.blogproject.model.dto.BlogPostDTO;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/blogpost")
@Tag(name = "BlogPost Resource", description = "BlogPost Management API")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BlogPostResource {

    @Inject
    BlogPostService blogPostService;

    // @GET
    // @PermitAll
    // @Operation(summary = "Get all BlogPosts", description = "Returns a list of BlogPosts.")
    // public Response getAllBlogPosts() {
    //     List<BlogPostDTO> blogPosts = blogPostService.getAllBlogPosts();
    //     return Response.ok(blogPosts).build();
    // }

    @GET
    @PermitAll
    @Operation(summary = "Get all BlogPosts", description = "Returns a list of BlogPosts with optional search and pagination.")
    public Response getAllBlogPosts(@QueryParam("searchString") String searchString, @QueryParam("page") @DefaultValue("1") int page) {
        List<BlogPostDTO> blogPosts = blogPostService.getBlogPosts(searchString, page);
        return Response.ok(blogPosts).build();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    @Operation(summary = "Get a BlogPost by id", description = "Returns a BlogPost by id")
    public Response getBlogPost(@PathParam("id") Long id) {
        return blogPostService.getBlogPostById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Authenticated
    @Operation(summary = "Add a new BlogPost", description = "Creates a new BlogPost")
    public Response addBlog(BlogPostDTO blogPostDTO, @HeaderParam("accountId") Long accountId) {
        try {
            BlogPostDTO createdBlogPost = blogPostService.addBlogPost(blogPostDTO, accountId);
            return Response.status(Response.Status.CREATED).entity(createdBlogPost).build();
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"admin", "moderator"})
    @Operation(summary = "Update a BlogPost", description = "Updates an existing BlogPost")
    public Response updateBlogPost(@PathParam("id") Long id, BlogPostDTO blogPostDTO) {
        try {
            blogPostService.updateBlogPost(id, blogPostDTO);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(Response.Status.NOT_FOUND, e.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "moderator"})
    @Operation(summary = "Delete a BlogPost", description = "Deletes an existing BlogPost")
    public Response deleteBlogPost(@PathParam("id") Long id) {
        try {
            blogPostService.deleteBlogPost(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(Response.Status.NOT_FOUND, e.getMessage());
        }
    }

    private Response buildErrorResponse(Response.Status status, String message) {
        return Response.status(status).entity(message).build();
    }
}
