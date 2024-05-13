package org.tallerjava.modulosucive.dominio.repositorio;
import java.time.LocalDate;


public class PasadaPorPeaje {
    private LocalDate fecha;
    private double costo;
    private DataTipoCobro.Tipo tipoCobro;
    Vehiculo vehiculo;

    public PasadaPorPeaje() {
    }

    public PasadaPorPeaje(double costo, LocalDate fechaActual, DataTipoCobro.Tipo tipoCobro, Vehiculo vehiculo) {
        this.costo = costo;
        this.fecha = fecha;
        this.tipoCobro = tipoCobro;
        this.vehiculo = vehiculo;
    }


    public double getCosto() {
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

    public DataTipoCobro.Tipo getTipoCobro() {
        return tipoCobro;
    }

    public void setTipoCobro(DataTipoCobro.Tipo tipoCobro) {
        this.tipoCobro = tipoCobro;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
