package org.acme.rest;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Putnik;
import org.acme.model.TimeResponse;
import org.acme.model.TimezoneRecord;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/vrijeme")
@PermitAll
public class TimeResource {

    @Inject
    @RestClient
    IpifyApi ipifyApi;

    @Inject
    @RestClient
    TimeApi timeApi;

    @Inject
    EntityManager em;

    @GET
    @Path("/sacuvaj/{putnikId}")
    @Transactional
    public Response obradiIVrati(@PathParam("putnikId") Long putnikId) {

        String mojIp = ipifyApi.getIp();
        TimeResponse tr = timeApi.getTime(mojIp);

        Putnik p = em.find(Putnik.class, putnikId);

        if (p == null) {
            return Response.status(404).entity("Putnik ne postoji!").build();
        }

        TimezoneRecord noviZapis = new TimezoneRecord();
        noviZapis.ip = mojIp;
        noviZapis.city = tr.timeZone;
        noviZapis.time = tr.time;
        noviZapis.putnik = p;

        em.persist(noviZapis);

        return Response.ok(noviZapis).build();
    }
}