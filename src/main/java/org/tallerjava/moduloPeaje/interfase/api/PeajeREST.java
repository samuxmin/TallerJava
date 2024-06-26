package org.tallerjava.moduloPeaje.interfase.api;

import org.tallerjava.moduloPeaje.aplicacion.PeajeService;
import org.tallerjava.moduloPeaje.dominio.DataIdentificador;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/peaje")
public class PeajeREST {

    @Inject
    private PeajeService peajeService;

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    public String saludo() {
        return "{\"saludo\":\"hola desde api peaje\"}";
    }

    //http://localhost:8080/TallerJava/api/peaje/tarifaComun
    @POST
    @Path("/tarifaComun")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public boolean actualizartarifaComun(double importe) {
        peajeService.actualizarTarifaComun(importe);
        return true;
    }

    //http://localhost:8080/TallerJava/api/peaje/tarifaPreferencial
    @POST
    @Path("/tarifaPreferencial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public boolean actualizartarifaPreferencial(double importe) {
        peajeService.actualizarTarifaPreferencial(importe);
        return true;
    }

    /* 
    @GET
    @Path("/vehiculos/pasadaPorPeaje")
    @Produces({ MediaType.APPLICATION_JSON })
    public boolean getVehiculosUsr(@PathParam("tag") String tag) {
        return peajeService.estaHabilitadoSincronico(tag, tag);
    }
     */

    @POST
    @Path("/vehiculos/pasadaPorPeaje")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getVehiculosUsr(DataIdentificador dataIdentificador) {
        boolean pasa = peajeService.estaHabilitadoSincronico(dataIdentificador.getTag(), dataIdentificador.getMatricula());
        if (pasa) {
            return Response.ok(true).build();  // Devuelve 200 OK con true como entidad
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("No esta habilitado").build();
        }
    }
    /*    
    @POST
    @Path("/tarifaPreferencial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON })
    public boolean actualizartarifaPreferencial(double importe) {
        peajeService.actualizarTarifaPreferencial(importe);
        return true;
    } */

}
