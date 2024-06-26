package org.tallerjava.moduloGestionCliente.interfase.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteService;
import org.tallerjava.moduloGestionCliente.dominio.clases.Cuenta;
import org.tallerjava.moduloGestionCliente.dominio.clases.PasadaPorPeaje;
import org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloGestionCliente.dominio.datatypes.DataVehiculo;
import org.tallerjava.moduloGestionCliente.dominio.datatypes.RangoFechas;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuarios")
@DenyAll
public class GestionClienteREST {

    @Inject
    private GestionClienteService gestionClienteService;

    @PermitAll
    @GET
    @Path("/")

    @Produces({MediaType.APPLICATION_JSON})
    public String saludo() {
        return "{\"saludo\":\"hola desde api gestion usuario\"}";
    }

    @GET
    @Path("/{ci}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("usuario")
    public Usuario getUsuario(@PathParam("ci") String ciUsr) {
        return gestionClienteService.getUsuario(ciUsr);
    }

    @PermitAll
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public boolean altaUsuario(Usuario usuario) {

        return gestionClienteService.altaClienteTelepeaje(usuario);
    }

    @RolesAllowed("usuario")
    @POST
    @Path("/{ci}/vehiculos")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean vincularVehiculo(@PathParam("ci") String ciUsr, DataVehiculo vehiculo) {

        return gestionClienteService.vincularVehiculo(vehiculo, ciUsr);
    }

    @RolesAllowed("usuario")
    @GET
    @Path("/{ci}/vehiculos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DataVehiculo> getVehiculosUsr(@PathParam("ci") String ci) {
        List<DataVehiculo> vehiculosDatas = new ArrayList<>();
        List<Vehiculo> vehiculos = gestionClienteService.mostraVehiculosVinculados(ci);
        for (Vehiculo vehiculo : vehiculos) {
            vehiculosDatas.add(new DataVehiculo(vehiculo));

        }
        return vehiculosDatas;
    }

    @RolesAllowed("usuario")
    @DELETE
    @Path("/vehiculos/{tag}")
    @Produces({MediaType.APPLICATION_JSON})
    public boolean desvincularVehiculo(@PathParam("tag") String tag) {
        System.out.println("hoalas");
        return gestionClienteService.desvincularVehiculo(tag);
    }

    @RolesAllowed("usuario")
    @GET
    @Path("/{ci}/pasadas")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PasadaPorPeaje> consultarPasadas(@PathParam("ci") String ci, RangoFechas rangoFechas) {
        LocalDate fechaInicio = LocalDate.parse(rangoFechas.getFechaInicio());
        LocalDate fechaFin = LocalDate.parse(rangoFechas.getFechaFin());
        return gestionClienteService.consultarPasadas(ci, fechaInicio, fechaFin);
    }

    @RolesAllowed("usuario")
    @GET
    @Path("/{ci}/pasadas/{tag}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PasadaPorPeaje> consultarPasadas(@PathParam("ci") String ci, @PathParam("tag") String tag, RangoFechas rangoFechas) {
        LocalDate fechaInicio = LocalDate.parse(rangoFechas.getFechaInicio());
        LocalDate fechaFin = LocalDate.parse(rangoFechas.getFechaFin());
        return gestionClienteService.consultarPasadas(ci, tag, fechaInicio, fechaFin);
    }

    @RolesAllowed("usuario")
    @GET
    @Path("/{ci}/saldo")
    @Produces({MediaType.APPLICATION_JSON})
    public double consultarSaldo(@PathParam("ci") String ci) {
        return gestionClienteService.consultarSaldo(ci);
    }

    @RolesAllowed("usuario")
    @POST
    @Path("/{ci}/saldo")
    @Produces({MediaType.APPLICATION_JSON})
    public boolean cargarSaldo(@PathParam("ci") String ci, double importe) {
        return gestionClienteService.cargarSaldo(ci, importe);
    }

    @RolesAllowed("usuario")
    @POST
    @Path("/{ci}/tarjeta")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean asociarTarjeta(@PathParam("ci") String ci, Tarjeta tarjeta) {
        return gestionClienteService.asociarTarjeta(ci, tarjeta);
    }

    @RolesAllowed("usuario")
    @GET
    @Path("/{ci}/cuentas")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cuenta> consultarCuentas(@PathParam("ci") String ci) {
        return gestionClienteService.obtenerCuentasPorCI(ci);
    }

    @RolesAllowed("usuario")
    @POST
    @Path("/{tag}/realizarPostPago")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean realizarPostPago(@PathParam("tag") String tag, double importe) {
        return gestionClienteService.realizarPostPago(importe, tag);
    }

    @RolesAllowed("usuario")
    @POST
    @Path("/{tag}/realizarPrePago")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean realizarPrePago(@PathParam("tag") String tag, double importe) {
        return gestionClienteService.realizarPrePago(importe, tag);
    }
    
}
