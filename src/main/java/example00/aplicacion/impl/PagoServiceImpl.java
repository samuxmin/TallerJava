package example00.aplicacion.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example00.aplicacion.PagoService;
import example00.dominio.Pago;
import example00.dominio.PagoResponse;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class PagoServiceImpl implements PagoService {
    List<Pago> pagos;
    private static final List<String> MATRICULAS_ERROR = Arrays.asList("ABC123", "XYZ789");
    private Random random = new Random();

    @PostConstruct
    private void inicializar() {
        System.out.println("Inicializando Pagos");
        this.pagos = new ArrayList<>();
        pagos.add(new Pago(1, 1000));
        pagos.add(new Pago(2, 2000));
    }

    @Override
    public PagoResponse realizarPago(String matricula, int monto) {
        if (MATRICULAS_ERROR.contains(matricula)) {
            return new PagoResponse(false, "Error: Matricula retorna error", null);
        }

        boolean pagoExitoso = random.nextBoolean();
        if (pagoExitoso) {
            int idPago = pagos.size() + 1;
            Pago pago = new Pago(idPago, monto);
            pagos.add(pago);
            return new PagoResponse(true, "Pago confirmado", idPago);
        } else {
            return new PagoResponse(false, "Pago rechazado", null);
        }
    }
}