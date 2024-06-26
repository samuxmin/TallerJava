package org.tallerjava.moduloMediosDePago.interfase.api;

import java.time.LocalDate;
import java.util.List;

import org.tallerjava.moduloGestionCliente.interfase.evento.out.AsociarTarjeta;
import org.tallerjava.moduloMediosDePago.aplicacion.MetodoPagoService;
import org.tallerjava.moduloMediosDePago.dominio.Pago;
import org.tallerjava.moduloMediosDePago.dominio.RangoFechas;
import org.tallerjava.moduloMediosDePago.dominio.Usuario;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/mediosDePago")
public class ModuloMediosDePagoREST {

    @Inject
    private MetodoPagoService metodoPagoService;

    @POST
    @Path("/altaCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean altaCliente(AsociarTarjeta asociarTarjeta) {
        
        return metodoPagoService.altaCliente(asociarTarjeta);
    }
/* 
    @POST
    @Path("/notificarPago/{ci}/{tag}/{importe}/{nroTarjeta}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean notificarPago(@PathParam("ci") String ci, @PathParam("tag") String tag, @PathParam("importe") double importe, @PathParam("nroTarjeta") int nroTarjeta) {
        
        return metodoPagoService.notificarPago(ci, tag, importe, nroTarjeta);
    }
*/
    @POST
    @Path("/notificarPago/{ci}/{tag}/{importe}/{nroTarjeta}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean notificarPago(@PathParam("ci") String ci, @PathParam("tag") String tag, @PathParam("importe") double importe, @PathParam("nroTarjeta") int nroTarjeta) {
        try {
            return metodoPagoService.notificarPago(ci, tag, importe, nroTarjeta);
        } catch (Exception e) {
            System.err.println("Error al notificar el pago: " + e.getMessage());
            return false;
        }
    }
    
    @GET
    @Path("/consultarPagosPorDia/{fechaInicio}/{fechaFin}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> consultarPagosPorDia(@PathParam("fechaInicio") String fechaInicio, @PathParam("fechaFin") String fechaFin) {
        RangoFechas rangoFechas = new RangoFechas(LocalDate.parse(fechaInicio), LocalDate.parse(fechaFin));

        return metodoPagoService.consultaDePagosPorDia(rangoFechas);
    }

    @GET
    @Path("/consultarPagosPorCliente/{ci}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> consultarPagosPorCliente(@PathParam("ci") String ci) {
        Usuario usuario = new Usuario();
        usuario.setCi(ci);

        return metodoPagoService.consultaDePagosPorCliente(usuario);
    }

    @GET
    @Path("/consultarPagosClienteVehiculo/{ci}/{tag}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> consultarPagosClienteVehiculo(@PathParam("ci") String ci, @PathParam("tag") String tag) {
        Usuario usuario = new Usuario();
        usuario.setCi(ci);
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTag(tag);

        return metodoPagoService.consultaDePagosClienteVehiculo(usuario, vehiculo);
    }

    
}
