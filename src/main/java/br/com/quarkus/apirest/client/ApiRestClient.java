package br.com.quarkus.apirest.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("rest-client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiRestClient implements StarshipsService {

    @RestClient
    StarshipsService rc;

    @Override
    public String starships() {
        return rc.starships();
    }
}
