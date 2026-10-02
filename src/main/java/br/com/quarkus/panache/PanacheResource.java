package br.com.quarkus.panache;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("panache")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PanacheResource {

    @POST
    @Transactional
    public void add(Pessoa pessoa) {
        pessoa.id = null;
        pessoa.persist();
    }

    @GET
    public List<Pessoa> listAll() {
        return Pessoa.listAll();
    }

    @GET
    @Path("listByAge")
    public List<Pessoa> listByAge(@QueryParam("idade") int idade) {
        return Pessoa.findByIdade(idade);
    }

    @PUT
    @Transactional
    public Pessoa edit(
            @QueryParam("id") int id,
            @QueryParam("idade") int idade
    ) {
        Pessoa p = Pessoa.findById(id);
        p.idade = idade;
        p.persist();
        return p;
    }

    @DELETE
    @Transactional
    public void delete(@QueryParam("id") int id) {
//        Pessoa.delete("id", id);
        Pessoa.deleteById(id);
    }
}
