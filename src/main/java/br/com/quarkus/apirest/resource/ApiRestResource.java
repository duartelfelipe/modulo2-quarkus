package br.com.quarkus.apirest.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("rest-api")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class ApiRestResource {

    private int i = 0;

    @GET
    public int getI() {
        return i;
    }

    @POST
    public void addI() {
        i++;
    }

    @PUT
    public void setI(int i) {
        this.i = i;
    }

    @DELETE
    public void deleteI() {
        i--;
    }

}
