package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

import jakarta.persistence.ManyToOne;


public class PasadaPorPeaje {

    private long id;
    private LocalDate fecha;
    private double costo;
    private DataTipoCobro tipoCobro;

    Vehiculo vehiculo;


    private boolean pagada = false; //PROBANDO COSAS 1


    public PasadaPorPeaje(double costo, LocalDate fecha, DataTipoCobro tipoCobro,Vehiculo vehiculo) {
        this.costo = costo;
        this.fecha = fecha;
        this.tipoCobro = tipoCobro;
        this.vehiculo = vehiculo;
    }

    public PasadaPorPeaje() {
    }

    //PROBANDO COSAS 1

    public boolean estaPagada() {    
        return this.pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
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
