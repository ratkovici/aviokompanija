package org.acme.rest;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.model.TimeResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/time/current")
@RegisterRestClient(configKey = "time-api")
public interface TimeApi {

    @GET
    @Path("/ip")
    public TimeResponse getTime(@QueryParam("ipAddress") String ipAddress);
}
