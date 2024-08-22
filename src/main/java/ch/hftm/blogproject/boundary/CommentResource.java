package ch.hftm.blogproject.boundary;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.hftm.blogproject.boundary.dto.CommentDTO;
import ch.hftm.blogproject.control.CommentService;
import ch.hftm.blogproject.entity.Comment;
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

@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Comment Resource", description = "Comment Management API")
@DenyAll
public class CommentResource {
    
    @Inject
    CommentService commentService;

    @GET
    @Path("/{blogPostId}")
    @PermitAll
    @Operation(summary = "Get comments for a blog", description = "Returns all comments for a specific blog post")
    public Response getCommentsByBlog(@PathParam("blogPostId") Long blogPostId) {
        List<Comment> comments = commentService.getCommentsByBlog(blogPostId);
        if (comments != null) {
            List<CommentDTO> commentDTOs = comments.stream().map(CommentDTO::new).collect(Collectors.toList());
            return Response.status(Status.OK).entity(commentDTOs).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Blog not found").build();
        }
    }

    @POST
    @Path("/{blogPostId}")
    @RolesAllowed({"admin", "moderator", "user"})
    @Operation(summary = "Add a comment to a blog", description = "Adds a comment to a specific blog post")
    public Response addCommentToBlog(@PathParam("blogPostId") Long blogPostId, @HeaderParam("accountId") Long accountID, CommentDTO commentDTO) {
        try {
            Comment createdComment = commentService.addComment(commentDTO ,blogPostId, accountID);
            return Response.status(Status.CREATED).entity(new CommentDTO(createdComment)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_FOUND).entity("BlogPost not found").build();
        }
    }

    @PATCH
    @Path("/{blogPostId}")
    @RolesAllowed({"admin", "moderator", "user"})
    @Operation(summary = "Update a comment", description = "Updates an existing comment")
    public Response updateComment(@PathParam("blogPostId") Long blogPostId, @HeaderParam("commentId") Long commentId, CommentDTO  commentDTO) {
        Comment existingComment = commentService.getCommentById(commentId);
        if (existingComment != null) {
            commentService.updateComment(commentId, commentDTO);
            return Response.status(Status.OK).entity(new CommentDTO(existingComment)).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Comment not found").build();
        }
    }

    @DELETE
    @RolesAllowed({"admin", "moderator", "user"})
    @Operation(summary = "Delete a comment", description = "Deletes an existing comment")
    public Response deleteComment(@HeaderParam("commentId") Long commentId) {
        if (commentId == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            commentService.deleteComment(commentId);
            return Response.noContent().build();
        }
    }
}
