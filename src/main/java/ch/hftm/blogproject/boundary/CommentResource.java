package ch.hftm.blogproject.boundary;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.hftm.blogproject.boundary.dto.BlogPostDTO;
import ch.hftm.blogproject.boundary.dto.CommentDTO;
import ch.hftm.blogproject.control.CommentService;
import ch.hftm.blogproject.entity.BlogPost;
import ch.hftm.blogproject.entity.Comment;
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

@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Comment Resource", description = "Comment Management API")
@DenyAll
public class CommentResource {
    
    @Inject
    CommentService commentService;

    // @GET
    // @PermitAll
    // @Operation(summary = "Get all Comments", description = "Returns a list of Comments.")
    // public Response getAllComments() {
    //     List<Comment> comments = commentService.getAllComments();
    //     if (comments == null) {
    //         return Response.status(Status.NOT_FOUND).entity("No comments found").build();
    //     }
    //     return Response.ok(comments).build();
    // }

    @GET
    @Path("/{blogPostId}")
    @PermitAll
    @Operation(summary = "Get comments for a blog", description = "Returns all comments for a specific blog post")
    public Response getCommentsByBlog(@PathParam("blogPostId") Long blogPostId) {
        if (blogPostId == null) {
            return Response.status(Status.BAD_REQUEST).entity("Blog post ID is required").build();
        }
        List<CommentDTO> comments = commentService.getCommentsByBlog(blogPostId);
        
        if (comments == null) {
            return Response.status(Status.NOT_FOUND).entity("No comments found for the given blog post ID").build();
        }
        return Response.ok(comments).build();
    }




    @POST
    @Path("/{blogPostId}")
    @Authenticated
    @Operation(summary = "Add a comment to a blog", description = "Adds a comment to a specific blog post")
    public Response addCommentToBlog(@PathParam("blogPostId") Long blogPostId, @HeaderParam("accountId") Long accountID, CommentDTO commentDTO) {
        try {
            commentService.addComment(commentDTO ,blogPostId, accountID);
            return Response.status(Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // @PATCH
    // @Path("/{blogPostId}")
    // @RolesAllowed({"admin", "moderator"})
    // @Operation(summary = "Update a comment", description = "Updates an existing comment")
    // public Response updateComment(@PathParam("blogPostId") Long blogPostId, @HeaderParam("commentId") Long commentId, CommentDTO  commentDTO) {
    //     Comment existingComment = commentService.getCommentById(commentId);
    //     if (existingComment != null) {
    //         commentService.updateComment(commentId, commentDTO);
    //         return Response.status(Status.OK).entity(new CommentDTO(existingComment)).build();
    //     } else {
    //         return Response.status(Status.NOT_FOUND).entity("Comment not found").build();
    //     }
    // }

//     @DELETE
//     @RolesAllowed({"admin", "moderator"})
//     @Operation(summary = "Delete a comment", description = "Deletes an existing comment")
//     public Response deleteComment(@HeaderParam("commentId") Long commentId) {
//         if (commentId == null) {
//             return Response.status(Status.BAD_REQUEST).build();
//         } else {
//             commentService.deleteComment(commentId);
//             return Response.noContent().build();
//         }
//     }
}
