package org.tallerjava.moduloMonitoreo.aplicacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tallerjava.moduloMonitoreo.dominio.clases.Vehiculo;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class PasajeVehiculo {
    Vehiculo vehiculo;
    LocalDate fecha;
}
