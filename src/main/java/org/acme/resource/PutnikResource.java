package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exceptions.PutnikException;
import org.acme.model.Karta;
import org.acme.model.Putnik;
import org.acme.service.PutnikService;

import java.util.List;

@Path("/putnik")
public class PutnikResource {

    @Inject
    PutnikService putnikService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addPutnik")
    public Response addPutnik(Putnik putnik) {
        try {
            putnikService.createPutnik(putnik);
        } catch (PutnikException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllPutnici")
    public Response getAllPutnici() {
        List<Putnik> putnici = null;
        try {
            putnici = putnikService.getAllPutnici();
        } catch (PutnikException e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(putnici).build();
    }

    @GET
    @Path("/getPutnikByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPutnikByName(@QueryParam("name") String name) {
        List<Putnik> putnici = putnikService.getPutnikByName(name);
        return Response.ok().entity(putnici).build();
    }

    @GET
    @Path("/karte/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKarteByPutnikId(@PathParam("id") Long id) {
        List<Karta> karte = putnikService.getKarteByPutnikId(id);
        return Response.ok().entity(karte).build();
    }
}