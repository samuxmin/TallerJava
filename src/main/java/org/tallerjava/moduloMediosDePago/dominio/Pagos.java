
package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

public class Pagos {
    private double costo;
    private LocalDate fecha;
    private Usuario usuario;
    private Vehiculo vehiculo;

    public Pagos(double costo, LocalDate fecha, Usuario usuario, Vehiculo vehiculo) {
        this.costo = costo;
        this.fecha = fecha;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}