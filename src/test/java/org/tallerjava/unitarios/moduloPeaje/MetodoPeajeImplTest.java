package org.tallerjava.unitarios.moduloPeaje;

import org.junit.jupiter.api.Test;
import org.tallerjava.moduloPeaje.aplicacion.PeajeService;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeajeImpl;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.dominio.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class MetodoPeajeImplTest {
    PeajeService peajeService = new ServicioPeajeImpl();

    @Test
    public void estaHabilitadoSincronicoTest() {
        // Crear vehículos de ejemplo
        Vehiculo vehiculoNacional = new VehiculoNacional(new ArrayList<>(), null, new Matricula("123"), new Tag("123"));
        Vehiculo vehiculoExtranjero = new VehiculoExtranjero(new ArrayList<>(), null, new Tag("1234"));

        // Lista de vehículos
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculoNacional);
        vehiculos.add(vehiculoExtranjero);

        String id = "123"; // ID a buscar

        // Verificar cada vehículo
        boolean encontrado = false;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof VehiculoNacional) {
                VehiculoNacional vehiculoNac = (VehiculoNacional) vehiculo;
                if (vehiculoNac.getMatricula().getNroMatricula().equals(id)) {
                    encontrado = true;
                    break;
                }
            } else if (vehiculo instanceof VehiculoExtranjero) {
                VehiculoExtranjero vehiculoExt = (VehiculoExtranjero) vehiculo;
                if (vehiculoExt.getTag().getId().equals(id)) {
                    encontrado = true;
                    break;
                }
            }
        }

        // Verificar si se encontró el vehículo con el ID dado
        assertTrue(encontrado);
    }

    @Test
    public void actualizarTarifaComun() {
        TarifaComun tarifaComun= new TarifaComun(500);
        tarifaComun.setValor(450);
        
        assertEquals(450, tarifaComun.getValor(), 0); // Verificar que la tarifa se actualizo
    }

    @Test
    public void actualizarTarifaPreferencial() {
        TarifaPreferencial tarifaPref= new TarifaPreferencial(300);
        tarifaPref.setValor(250);

        assertEquals(250, tarifaPref.getValor(), 0); // Verificar que la tarifa se actualizo
    }

}
