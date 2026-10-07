package br.com.quarkus.jwt;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@RequestScoped
@Path("jwt")
public class JwtResource {

    @Claim(standard = Claims.preferred_username)
    String username;
    @Claim(standard = Claims.birthdate)
    String birthdate;

    @GET
    @Path("claim")
    @RolesAllowed("Subscriber")
    public String getUsernameClaim() {
        return username.concat("+").concat(birthdate);
    }
}
