package client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import example00.interfaces.ws.soap.Pago;
import example00.interfaces.ws.soap.PagoSOAPcli;
import example00.interfaces.ws.soap.PagoSOAPService;
import example00.interfaces.ws.soap.PagoArray;

class InvocarWSClient {

    @Test
    @DisplayName("Invocando a WS de pagos: obtener pagos")
    void obtenerPagos() {
        PagoSOAPService service = new PagoSOAPService();
        PagoSOAPcli pagoPort = service.getPagoSOAPPort();

        PagoArray pagos = pagoPort.obtenerPagos();
        for (Pago pago : pagos.getItem()) {
            System.out.println("Pago " + pago.getId() + ", monto: " + pago.getMonto());
        }
    }

    @Test
    @DisplayName("Invocando a WS de pagos: realizar pagos")
    void realizarPago() {
        PagoSOAPService service = new PagoSOAPService();
        PagoSOAPcli pagoPort = service.getPagoSOAPPort();

        // Datos del pago
        int monto = 1000;

        int idPago = pagoPort.realizarPago(monto);
        System.out.println("Pago Realizado. Código de confirmación: " + idPago);
    }
}
