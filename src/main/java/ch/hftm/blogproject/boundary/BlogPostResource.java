package ch.hftm.blogproject.boundary;

import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.hftm.blogproject.boundary.dto.BlogPostDTO;
import ch.hftm.blogproject.control.BlogPostService;
import ch.hftm.blogproject.entity.BlogPost;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.stream.Collectors;

// This class provides the REST API endpoints for managing blogposts.
@Path("/blogpost")
@DenyAll
@Tag(name = "BlogPost Resource", description = "BlogPost Management API")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BlogPostResource {

    @Inject
    BlogPostService blogPostService;
    
    @GET
    @PermitAll
    @Operation(summary = "Get all BlogPosts", description = "Returns a list of BlogPosts.")
    public List<BlogPostDTO> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        return blogPosts.stream().map(BlogPostDTO::new).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @PermitAll
    @Operation(summary = "Get a BlogPost by id", description = "Returns a BlogPost by id")
    public Response getBlogPost(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        BlogPostDTO blogPostDTO = new BlogPostDTO(blogPost);
        return Response.status(Status.OK).entity(blogPostDTO).build();
    }

    @POST
    @Authenticated
    @Operation(summary = "Add a new BlogPost", description = "Creates a new BlogPost")
    public Response addBlog(BlogPostDTO blogPostDTO, @HeaderParam("accountId") Long accountId) {
        try {
            blogPostService.addBlogPost(blogPostDTO, accountId);
            return Response.status(Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "moderator"})
    @Operation(summary = "Delete a BlogPost", description = "Deletes a BlogPost by its id")
    public Response deleteBlog(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        try {
            blogPostService.deleteBlogPost(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"admin", "moderator"})
    @Operation(summary = "Update a BlogPost", description = "Updates an existing BlogPost")
    public Response updateBlog(BlogPostDTO blogPostDTO, @PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        blogPostService.updateBlogPost(id, blogPostDTO);
        return Response.ok().build();
    }

}
    // @Authenticated
    // // @PermitAll
    // @GET
    // // @Authenticated
    // public Response getBlogPosts(@QueryParam("searchString") Optional<String> searchString, @QueryParam("page") Optional<Long> pageIndex) {
    //     return Response.status(Status.OK).entity(blogPostService.getAllBlogPosts(searchString, pageIndex)).build();
    // }

    // @GET
    // // @Authenticated
    // @Path("{id}")
    // public Response getBlog (long id) {
    //     return Response.status(Status.OK).entity(blogPostService.getBlogById(id)).build();
    // }
    
    // Old Code V1 before DTO implementation.
    // @POST
    // public Response addBlog(@Valid Blog blog, @Context UriInfo uriInfo) {
    //     if (blog.getTitle().isEmpty() || blog.getContent().isEmpty()) {
    //         return Response.status(Status.BAD_REQUEST).build();
    //     } else {
    //         Blog responseValue = blogService.pushBlog(blog);
    //         URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(blog.getId())).build();
    //         if (responseValue != null) {
    //             // return Response.status(Status.CREATED).created(uri).entity(responseValue).build();
    //             return Response.created(uri).entity(responseValue).status(Status.CREATED).build();
    //         } else {
    //             return Response.status(Status.BAD_REQUEST).build();
    //         }
    //     }
    // }

    // V2 with DTO implementation

    // Old Code V1 before DTO implementation.
    // @PUT
    // @Path("{id}")
    // public Response putBlog(@PathParam("id") long id, Blog blog) {
    //     if (blog.getTitle().isEmpty() || blog.getContent().isEmpty()) {
    //         return Response.status(Status.BAD_REQUEST).build();
    //     } else {
    //         Blog responseValue = blogService.putBlog(id, blog);
    //         if (responseValue != null) {
    //         return Response.status(Status.OK).entity(responseValue).build();
    //         } else {
    //             return Response.status(Status.NOT_FOUND).build();

    //         }
    //     }
    // }

    // V2 with DTO implementation
    // @PUT
    // @Path("{id}")
    // public Response putBlog(@PathParam("id") long id, @Valid NewBlogPostDTO blogDto ,@Context UriInfo uriInfo) {
    //     BlogPost updatedBlog = blogPostService.putBlog(id, blogDto.toBlog());
    //     return Response.created(uriInfo.getAbsolutePathBuilder().path(updatedBlog.getId().toString()).build()).build();
    // }

    // @PATCH
    // @Path("{id}")
    // public Response patchBlog(@PathParam("id") long id, BlogPost blog) {
    //     BlogPost responseValue = blogPostService.patchBlog(id, blog);
    //     if (responseValue != null) {
    //         return Response.status(Status.OK).entity(responseValue).build();
    //     } else {
    //         return Response.status(Status.NOT_FOUND).build();
    //     }
    // }

    // @GET
    // @Path("/{id}/comments")
    // public Response getComments(@PathParam("id") long id, @QueryParam("page") Optional<Long> pageIndex) {
    //     return Response.status(Status.OK).entity(commentService.getComments(id, pageIndex)).build();
    // }

    // @POST
    // @Path("/{id}/comments")
    // public Response addComment(@PathParam("id") long id, @Valid NewCommentDTO commentDTO, @Context UriInfo uriInfo) {
    //     Comment persistedComment = commentService.pushComment(id, commentDTO.toComment());
    //     return Response.created(uriInfo.getAbsolutePathBuilder().path(persistedComment.getId().toString()).build()).build();
    // }