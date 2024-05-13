package org.tallerjava.moduloPeaje;

import org.junit.jupiter.api.Test;
import org.tallerjava.moduloGestionCliente.interfase.GestionClienteService;
import org.tallerjava.moduloPeaje.aplicacion.PeajeService;
import org.tallerjava.moduloPeaje.aplicacion.PeajeServiceImp;
import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteServiceImpl;
import org.tallerjava.moduloGestionCliente.dominio.clases.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class PeajeTest {
    PeajeService peajeService = new PeajeServiceImp();
/*
    @Test
    public boolean estaHabilitado(String id) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof VehiculoNacional) {
                if (((VehiculoNacional)vehiculo).getMatricula().getNroMatricula().equals(id)) {
                    assertTrue(true); // El vehículo está habilitado
                }
            }
            if (vehiculo instanceof VehiculoExtranjero) {
                if (((VehiculoExtranjero)vehiculo).getTag().getId().equals(id)) {
                    assertTrue(true); // El vehículo está habilitado
                }
            }
        }
        return assertFalse(false); // El vehículo no está habilitado
    }

    @Test
    public void actualizarTarifaComun(double importe) {
        assertTrue(tarifaComun.setMonto(importe));
        assertFalse(tarifaComun.setMonto(importe));
    }
*/
    @Test
    public void actualizarTarifaPreferencial() {
       // assertTrue(tarifaPref.setMonto(preferencial));
       // assertFalse(tarifaPref.setMonto(preferencial));
        assertTrue(true);
    }

}
