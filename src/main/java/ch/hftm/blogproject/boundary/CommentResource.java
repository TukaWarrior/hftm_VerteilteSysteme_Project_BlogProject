package ch.hftm.blogproject.boundary;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.hftm.blogproject.boundary.dto.CommentDTO;
import ch.hftm.blogproject.control.CommentService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Comment Resource", description = "Comment Management API")
public class CommentResource {

    @Inject
    CommentService commentService;

    @GET
    @Path("/{blogPostId}")
    @PermitAll
    @Operation(summary = "Get comments for a blog", description = "Returns all comments for a specific blog post")
    public Response getCommentsByBlog(@PathParam("blogPostId") Long blogPostId) {
        try {
            List<CommentDTO> comments = commentService.getCommentsByBlog(blogPostId);
            return Response.ok(comments).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{blogPostId}")
    @Authenticated
    @Operation(summary = "Add a comment to a blog", description = "Adds a comment to a specific blog post")
    public Response addCommentToBlog(@PathParam("blogPostId") Long blogPostId, @HeaderParam("accountId") Long accountId, CommentDTO commentDTO) {
        try {
            CommentDTO createdComment = commentService.addComment(commentDTO, blogPostId, accountId);
            return Response.status(Response.Status.CREATED).entity(createdComment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{commentId}")
    @RolesAllowed({"admin", "moderator"})
    @Operation(summary = "Update a comment", description = "Updates an existing comment")
    public Response updateComment(@PathParam("commentId") Long commentId, CommentDTO commentDTO) {
        try {
            CommentDTO updatedComment = commentService.updateComment(commentId, commentDTO);
            return Response.ok(updatedComment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{commentId}")
    @RolesAllowed({"admin", "moderator"})
    @Operation(summary = "Delete a comment", description = "Deletes an existing comment")
    public Response deleteComment(@PathParam("commentId") Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
