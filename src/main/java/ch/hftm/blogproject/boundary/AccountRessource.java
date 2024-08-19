package ch.hftm.blogproject.boundary;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ch.hftm.blogproject.boundary.dto.NewAccountDTO;
import ch.hftm.blogproject.control.AccountService;
import ch.hftm.blogproject.entity.Account;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.MediaType;

// This class provides the REST API endpoints for managing blog posts.

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Account Resource", description = "Account Management API")
public class AccountRessource {

    @Inject
    AccountService accountService;
    
    @GET
    @Operation(summary = "Get all accounts", description = "Returns all accounts")
    public Response getAllAccounts() {
        return Response.status(Status.OK).entity(accountService.getAllAccounts()).build();
    }

    @POST
    @Operation(summary = "Add a new account", description = "Creates a new account")
    public Response addAccount(NewAccountDTO newAccountDTO, @Context UriInfo uriInfo) {;
        Account accountForResponse = accountService.addAccount(newAccountDTO.toAccount());
        return Response.created(uriInfo.getAbsolutePathBuilder().path(accountForResponse.getId().toString()).build()).build();
    }


    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete an account", description = "Deletes an account by its id")
    public Response deleteAccount(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        accountService.deleteAccount(id);
        return Response.status(Status.OK).build();
    }
}
