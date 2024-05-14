package org.tallerjava.moduloPeaje.interfase.evento.out;

import lombok.Getter;
import lombok.Setter;

public class PasajeVehiculo {
    @Getter
    @Setter
    private String descripcion;
    public PasajeVehiculo(String descripcion) {
        this.descripcion = descripcion;
    }
}
