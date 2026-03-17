package org.acme.rest;

import org.acme.model.Putnik;
import org.acme.service.PutnikService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/putnici")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PutnikResource {

    @Inject
    PutnikService service;

    @GET
    public List<Putnik> getAll() {
        return service.getAllPutnici();
    }

    @POST
    public Response add(Putnik putnik) {
        service.createPutnik(putnik);
        return Response.status(Response.Status.CREATED).entity(putnik).build();
    }
}