// package ch.hftm.blogproject.boundary;

// import java.util.List;
// import java.util.Optional;

// import org.eclipse.microprofile.jwt.JsonWebToken;
// import org.eclipse.microprofile.openapi.annotations.Operation;
// import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
// import org.eclipse.microprofile.openapi.annotations.tags.Tag;

// import ch.hftm.blogproject.control.BlogPostService;
// import ch.hftm.blogproject.model.dto.BlogPostDTO;
// import ch.hftm.blogproject.model.exception.DatabaseException;
// import ch.hftm.blogproject.model.exception.NotFoundException;
// import io.quarkus.security.Authenticated;
// import jakarta.annotation.security.PermitAll;
// import jakarta.annotation.security.RolesAllowed;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.*;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

// @Path("/blogpost")
// @Tag(name = "BlogPost Resource", description = "BlogPost Management API")
// @Consumes(MediaType.APPLICATION_JSON)
// @Produces(MediaType.APPLICATION_JSON)
// public class BlogPostResource {

//     @Inject
//     BlogPostService blogPostService;

//     @Inject
//     JsonWebToken jsonWebToken;

//     @GET
//     @PermitAll
//     @Operation(summary = "Get all BlogPosts", description = "Returns a list of BlogPosts with optional search and pagination.")
//     public Response getAllBlogPosts(@QueryParam("searchString") Optional<String> searchString, @QueryParam("page") Optional<Integer> page) {
//         try {
//             List<BlogPostDTO> blogPosts = blogPostService.getBlogPosts(searchString, page);
//             return Response.ok(blogPosts).build();
//         } catch (DatabaseException e) {
//             return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
//         }
//     }

//     @GET
//     @Path("/{id}")
//     @PermitAll
//     @Operation(summary = "Get a BlogPost by ID", description = "Returns a BlogPost by its ID.")
//     public Response getBlogPost(@PathParam("id") Long id) {
//         try {
//             BlogPostDTO blogPost = blogPostService.getBlogPostById(id);
//             return Response.ok(blogPost).build();
//         } catch (NotFoundException e) {
//             return buildErrorResponse(Response.Status.NOT_FOUND, e.getMessage());
//         } catch (DatabaseException e) {
//             return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
//         }
//     }

//     @POST
//     @Authenticated
//     @Operation(summary = "Add a new BlogPost", description = "Creates a new BlogPost")
//     public Response addBlogPost(BlogPostDTO blogPostDTO) {
//         try {
//             BlogPostDTO createdBlogPost = blogPostService.addBlogPost(blogPostDTO);
//             return Response.status(Response.Status.CREATED).entity(createdBlogPost).build();
//         } catch (DatabaseException e) {
//             return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
//         }
//     }

//     @PUT
//     @Path("/{id}")
//     @RolesAllowed({"admin", "moderator"})
//     @Operation(summary = "Update a BlogPost", description = "Updates an existing BlogPost")
//     public Response putBlogPost(@PathParam("id") Long id, BlogPostDTO blogPostDTO) {
//         try {
//             blogPostDTO.setBlogPostID(id); // Ensure the ID in DTO is the same as the path ID
//             BlogPostDTO updatedBlogPost = blogPostService.updateBlogPost(blogPostDTO);
//             return Response.ok(updatedBlogPost).build();
//         } catch (NotFoundException e) {
//             return buildErrorResponse(Response.Status.NOT_FOUND, e.getMessage());
//         } catch (DatabaseException e) {
//             return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
//         }
//     }

//     @PATCH
//     @Path("/{id}")
//     @RolesAllowed({"admin", "moderator"})
//     @Operation(summary = "Partially Update a BlogPost", description = "Partially updates an existing BlogPost")
//     public Response patchBlogPost(@PathParam("id") Long id, BlogPostDTO blogPostDTO) {
//         try {
//             blogPostDTO.setBlogPostID(id); // Ensure the ID in DTO is the same as the path ID
//             BlogPostDTO updatedBlogPost = blogPostService.updateBlogPost(blogPostDTO); // Reuse the update method for simplicity
//             return Response.ok(updatedBlogPost).build();
//         } catch (NotFoundException e) {
//             return buildErrorResponse(Response.Status.NOT_FOUND, e.getMessage());
//         } catch (DatabaseException e) {
//             return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
//         }
//     }

//     @DELETE
//     @Path("/{id}")
//     @RolesAllowed({"admin", "moderator"})
//     @Operation(summary = "Delete a BlogPost", description = "Deletes an existing BlogPost")
//     public Response deleteBlogPost(@PathParam("id") Long id) {
//         try {
//             BlogPostDTO deletedBlogPost = blogPostService.deleteBlogPost(id);
//             return Response.ok(deletedBlogPost).build();
//         } catch (NotFoundException e) {
//             return buildErrorResponse(Response.Status.NOT_FOUND, e.getMessage());
//         } catch (DatabaseException e) {
//             return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
//         }
//     }

//     @DELETE
//     @RolesAllowed({"admin"})
//     @Operation(summary = "Delete all BlogPosts", description = "Deletes all BlogPosts")
//     public Response deleteAllBlogPosts() {
//         try {
//             blogPostService.deleteAllBlogPosts();
//             return Response.noContent().build();
//         } catch (DatabaseException e) {
//             return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
//         }
//     }

//     private Response buildErrorResponse(Response.Status status, String message) {
//         return Response.status(status).entity(message).build();
//     }
// }
