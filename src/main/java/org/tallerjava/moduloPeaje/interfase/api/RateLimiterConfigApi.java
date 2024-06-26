package org.tallerjava.moduloPeaje.interfase.api;

import org.tallerjava.moduloPeaje.infraestructura.ratelimiter.RateLimiter;
import jakarta.annotation.security.DenyAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/config")
//@DenyAll
public class RateLimiterConfigApi {
    @Inject
    private RateLimiter rateLimiter;

    @GET
    @Path("/activarRateLimiter")
    @Produces({ MediaType.APPLICATION_JSON })
    public void activarRateLimiter(@QueryParam("valor") boolean nuevoEstado) {
        rateLimiter.activarRateLimiter(nuevoEstado);
    }
}