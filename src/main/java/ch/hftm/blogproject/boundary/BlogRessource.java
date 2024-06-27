package ch.hftm.blogproject.boundary;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import ch.hftm.blogproject.boundary.dto.NewBlogDTO;
import ch.hftm.blogproject.boundary.dto.NewCommentDTO;
import ch.hftm.blogproject.control.BlogService;
import ch.hftm.blogproject.control.CommentService;
import ch.hftm.blogproject.entity.Blog;
import ch.hftm.blogproject.entity.Comment;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

// This class provides the REST API endpoints for managing blog posts.

@Path("blogs")
public class BlogRessource {

    @Inject
    BlogService blogService;

    @Inject
    CommentService commentService;
    

    @GET
    public Response getBlogs(@QueryParam("searchString") Optional<String> searchString, @QueryParam("page") Optional<Long> pageIndex) {
        return Response.status(Status.OK).entity(blogService.getBlogs(searchString, pageIndex)).build();
    }

    @GET
    @Path("{id}")
    public Response getBlog (long id) {
        return Response.status(Status.OK).entity(blogService.getBlogById(id)).build();
    }
    
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
    @POST
    public Response addBlog(@Valid NewBlogDTO blogDto, @Context UriInfo uriInfo) {
        Blog persistedBlog = blogService.pushBlog(blogDto.toBlog());
        return Response.created(uriInfo.getAbsolutePathBuilder().path(persistedBlog.getId().toString()).build()).build();
    }


    @DELETE
    @Path("{id}")
    public Response deleteBlog(@PathParam("id") long id) {
        Blog responseValue = blogService.deleteBlog(id);
        if (responseValue != null) {
            return Response.status(Status.OK).entity(responseValue).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

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
    @PUT
    @Path("{id}")
    public Response putBlog(@PathParam("id") long id, @Valid NewBlogDTO blogDto ,@Context UriInfo uriInfo) {
        Blog updatedBlog = blogService.putBlog(id, blogDto.toBlog());
        return Response.created(uriInfo.getAbsolutePathBuilder().path(updatedBlog.getId().toString()).build()).build();
    }

    @PATCH
    @Path("{id}")
    public Response patchBlog(@PathParam("id") long id, Blog blog) {
        Blog responseValue = blogService.patchBlog(id, blog);
        if (responseValue != null) {
            return Response.status(Status.OK).entity(responseValue).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }



    @GET
    @Path("/{id}/comments")
    public Response getComments(@PathParam("id") long id, @QueryParam("page") Optional<Long> pageIndex) {
        return Response.status(Status.OK).entity(commentService.getComments(id, pageIndex)).build();
    }

    @POST
    @Path("/{id}/comments")
    public Response addComment(@PathParam("id") long id, @Valid NewCommentDTO commentDTO, @Context UriInfo uriInfo) {
        Comment persistedComment = commentService.pushComment(id, commentDTO.toComment());
        return Response.created(uriInfo.getAbsolutePathBuilder().path(persistedComment.getId().toString()).build()).build();
    }
}
