package org.tallerjava.moduloPeaje.dominio;

import java.time.LocalDate;

public class Vinculo {
    private LocalDate fechaIni;
    private boolean activo;
    private Vehiculo vehiculo;

    public Vinculo() {}

    public Vinculo(boolean activo, LocalDate fechaIni, Vehiculo vehiculo) {
        this.activo = activo;
        this.fechaIni = fechaIni;
        this.vehiculo = vehiculo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
