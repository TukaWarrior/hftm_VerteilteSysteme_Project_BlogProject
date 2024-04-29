package ch.hftm.blogproject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/duck")
public class DuckScenery {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        StringBuilder sb = new StringBuilder();
        sb.append("Caution! There are ducks on the road!");
        sb.append("\n𓅭 𓅰   𓅭 𓅰  𓅭    𓅭    𓅭 𓅰𓅭 𓅰");
        sb.append("\n▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        return sb.toString();
    }
}