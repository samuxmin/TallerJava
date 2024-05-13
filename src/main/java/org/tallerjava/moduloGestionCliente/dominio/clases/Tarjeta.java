package org.tallerjava.moduloGestionCliente.dominio.clases;

import java.time.LocalDate;

public class Tarjeta {
    private int nro;
    private String nombre;
    private LocalDate fechaVto;

    public Tarjeta(LocalDate fechaVto, String nombre, int nro) {
        this.fechaVto = fechaVto;
        this.nombre = nombre;
        this.nro = nro;
    }

    public Tarjeta() {
    }

    public LocalDate getFechaVto() {
        return fechaVto;
    }

    public void setFechaVto(LocalDate fechaVto) {
        this.fechaVto = fechaVto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }
}
