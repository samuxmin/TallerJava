package example00.interfaces.ws.soap;

import java.util.List;

import example00.aplicacion.PagoService;
import example00.dominio.Pago;
import example00.dominio.PagoResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PagoSOAP {

    @Inject
    private PagoService pagoService;

    @WebMethod
    @WebResult(name = "PagoResponse")
    public PagoResponse realizarPago(@WebParam(name = "matricula") String matricula, @WebParam(name = "monto") int monto) {
        return pagoService.realizarPago(matricula, monto);
    }
}





