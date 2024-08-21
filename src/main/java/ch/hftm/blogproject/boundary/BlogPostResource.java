package ch.hftm.blogproject.boundary;

import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import ch.hftm.blogproject.control.BlogPostService;
import ch.hftm.blogproject.control.CommentService;
import ch.hftm.blogproject.entity.Blog;
import jakarta.annotation.security.DenyAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

// This class provides the REST API endpoints for managing blog posts.

@DenyAll
@Path("/blogpost")
public class BlogPostResource {

    @Inject
    BlogPostService blogPostService;
    @Inject
    CommentService commentService;
    
    @GET
    @Operation(summary = "Get all BlogPosts", description = "Returns all BlogPosts")
    public List<Blog> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a BlogPost by id", description = "Returns a BlogPost by id")
    public Response getBlogPost(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        Blog blogPost = blogPostService.getBlogPostById(id);
        if (blogPost == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(blogPost).build();
    }

    @POST
    @Operation(summary = "Add a new BlogPost", description = "Creates a new BlogPost")
    public Response addBlog(Blog blogPost, @HeaderParam("authorId") Long accountId) {
        try {
            blogPostService.addBlogPost(blogPost, accountId);
            return Response.status(Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a BlogPost", description = "Deletes a BlogPost by its id")
    public Response deleteBlog(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        blogPostService.deleteBlogPost(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Update a BlogPost", description = "Updates an existing BlogPost")
    public Response updateBlog(@PathParam("id") Long id, Blog blogPost) {
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        blogPostService.updateBlogPost(id, blogPost);
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