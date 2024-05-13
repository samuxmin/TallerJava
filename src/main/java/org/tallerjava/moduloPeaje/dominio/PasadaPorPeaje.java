package org.tallerjava.moduloPeaje.dominio;

import java.time.LocalDate;

public class PasadaPorPeaje {
    private LocalDate fecha;
    private int costo;
    private DataTipoCobro tipoCobro;
    Vehiculo vehiculo;

    public PasadaPorPeaje(int costo, LocalDate fecha, DataTipoCobro tipoCobro,Vehiculo vehiculo) {
        this.costo = costo;
        this.fecha = fecha;
        this.tipoCobro = tipoCobro;
        this.vehiculo = vehiculo;
    }

    public PasadaPorPeaje() {
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public DataTipoCobro getTipoCobro() {
        return tipoCobro;
    }

    public void setTipoCobro(DataTipoCobro tipoCobro) {
        this.tipoCobro = tipoCobro;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
