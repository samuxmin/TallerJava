package org.tallerjava.moduloPeaje.dominio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity(name = "PasadaPorPeajePeaje")
public class PasadaPorPeajePeaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate fecha;
    private double costo;
    private DataTipoCobro tipoCobro;
    @ManyToOne
    Vehiculo vehiculo;

    public PasadaPorPeajePeaje(double costo, LocalDate fecha, DataTipoCobro tipoCobro, Vehiculo vehiculo) {
        this.costo = costo;
        this.fecha = fecha;
        this.tipoCobro = tipoCobro;
        this.vehiculo = vehiculo;
    }

    public PasadaPorPeajePeaje() {
    }
    public long getId(){return id;}

    public void setId(long id){this.id = id;}
    
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
