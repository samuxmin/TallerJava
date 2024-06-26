package org.tallerjava.moduloSucive.interfase.api;

import org.tallerjava.moduloSucive.interfase.api.classes.PagoResponse;
import org.tallerjava.moduloSucive.interfase.api.classes.PagoSOAP;
import org.tallerjava.moduloSucive.interfase.api.classes.PagoSOAPService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

//curl -X POST http://localhost:8080/TallerJava/api/pagos/realizar -H "Content-Type: application/json" -d '{"monto": 1000, "matricula": "ABC12"}'

@ApplicationScoped
@Path("/pagos")
public class SuciveController {

    @POST
    @Path("/realizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PagoResponse realizarPago(PagoRequest request) {
        PagoSOAPService service = new PagoSOAPService();
        PagoSOAP pagoPort = service.getPagoSOAPPort();
        // Datos del pago desde la solicitud
        int monto = request.getMonto();
        String matricula = request.getMatricula();
        // Llamada al servicio SOAP para realizar el pago
        PagoResponse response = pagoPort.realizarPago(matricula, monto);
        // Devolver la respuesta del servicio SOAP
        return response;
    }
}