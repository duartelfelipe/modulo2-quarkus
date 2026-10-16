package br.com.quarkus.opentelemetry;

import br.com.quarkus.panache.Pessoa;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("telemetry")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TelemetryResource {

    @GET
//    @Counted("counted.PessoaListAll")
    public List<Pessoa> listAll() {
        return Pessoa.listAll();
    }
}
