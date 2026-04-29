package org.acme.resource;


import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.model.CurrencyResponse;
import org.acme.model.Radnik;
import org.acme.rest.RatesApi;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@RolesAllowed("admin")
@Path("/curr")
public class CurrencyResource {

    @RestClient
    @Inject
    RatesApi ratesapi;

    @Inject
    EntityManager em;

    @GET
    @Path("/currencyConversion")
    @Transactional
    public Response currencyConversion(@QueryParam("from") String from,
                                       @QueryParam("to") String to,
                                       @QueryParam("value") double value,
                                       @QueryParam("userId") long userId){

        Radnik r = em.find(Radnik.class, userId);

        if(r == null){

            throw new WebApplicationException("Nema radnika sa tim id-jem" , 404);

        }

        CurrencyResponse cr = ratesapi.getRates(from, to);


        double convertedValue = value * cr.getRate();
        cr.setConvertedValue(convertedValue);
        cr.setValue(value);
        cr.setRadnik(r);

        em.persist(cr);

        return Response.ok(cr).build();

    }



}
