package example00.aplicacion;

import java.util.List;

import example00.dominio.Pago;
import example00.dominio.PagoResponse;

public interface PagoService {
    public PagoResponse realizarPago(String matricula, int monto);
}
