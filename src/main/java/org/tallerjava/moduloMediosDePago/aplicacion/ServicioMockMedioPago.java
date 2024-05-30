package org.tallerjava.moduloMediosDePago.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.Random;

@ApplicationScoped
@Path("/mockMediosDePago")
public class ServicioMockMedioPago {

    private int TARJETA_VALIDA = 111111111;
    private int TARJETA_INVALIDA = 222222222;
    private int TARJETA_BLOQUEADA = 333333333;

    @POST
    @Path("/notificarPago/{nroTarjeta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notificarPago(@PathParam("nroTarjeta") long nroTarjeta) {

        if (nroTarjeta == TARJETA_VALIDA) {

            return Response.ok().entity("Pago confirmado. Código de autorización: ABC123").build();
        } else if (nroTarjeta == TARJETA_INVALIDA) {

            return Response.status(Status.BAD_REQUEST).entity("Pago rechazado. Tarjeta inválida").build();
        } else if (nroTarjeta == TARJETA_BLOQUEADA) {

            return Response.status(Status.FORBIDDEN).entity("Pago rechazado. Tarjeta bloqueada").build();
        } else {

            Random random = new Random();
            boolean pagoExitoso = random.nextInt(6) < 5;

            if (pagoExitoso) {

                return Response.ok().entity("Pago confirmado. Código de autorización: DFG456").build();
            } else {
                return Response.status(Status.BAD_REQUEST).entity("Pago rechazado. Error al procesar el pago").build();
            }
        }
    }
}
