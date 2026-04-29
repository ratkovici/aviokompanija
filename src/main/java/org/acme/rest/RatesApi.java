package org.acme.rest;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.model.CurrencyResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://api.euroratesapi.dev/api")
public interface RatesApi {

    @GET
    @Path("/rates")
    CurrencyResponse getRates(@QueryParam("from") String from,
                              @QueryParam("to") String to);


}
