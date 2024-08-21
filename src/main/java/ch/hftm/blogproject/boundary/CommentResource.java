package ch.hftm.blogproject.boundary;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.hftm.blogproject.control.CommentService;
import ch.hftm.blogproject.entity.Comment;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Comment Resource", description = "Comment Management API")
public class CommentResource {
    
    @Inject
    CommentService commentService;

    @POST
    @Path("/{blogId}")
    @Operation(summary = "Add a comment to a blog", description = "Adds a comment to a specific blog post")
    public Response addCommentToBlog(@PathParam("blogId") Long blogPostId, Comment comment) {
        try {
            Comment createdComment = commentService.addComment(comment.getContent(), comment.getAccountId(), blogPostId);
            return Response.status(Status.CREATED).entity(createdComment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_FOUND).entity("Blog not found").build();
        }
    }

    @GET
    @Path("/{blogId}")
    @Operation(summary = "Get comments for a blog", description = "Returns all comments for a specific blog post")
    public Response getCommentsByBlog(@PathParam("blogId") Long blogPostId) {
        List<Comment> comments = commentService.getCommentsByBlog(blogPostId);
        if (comments != null) {
            return Response.status(Status.OK).entity(comments).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Blog not found").build();
        }
    }

    @PATCH
    @Path("/{commentId}")
    @Operation(summary = "Update a comment", description = "Updates an existing comment")
    public Response updateComment(@PathParam("commentId") Long commentId, Comment updatedComment) {
        Comment existingComment = commentService.getCommentById(commentId);
        if (existingComment != null) {
            commentService.updateComment(commentId, updatedComment);
            return Response.status(Status.OK).entity(existingComment).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Comment not found").build();
        }
    }
}
