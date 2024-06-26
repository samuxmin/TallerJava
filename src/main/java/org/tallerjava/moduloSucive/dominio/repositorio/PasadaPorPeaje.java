package org.tallerjava.moduloSucive.dominio.repositorio;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "PasadaPorPeajeSucive")
@Entity
public class PasadaPorPeaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private double costo;
    private DataTipoCobro tipoCobro;

    @ManyToOne
    Vehiculo vehiculo;

    public PasadaPorPeaje() {
    }

    public PasadaPorPeaje(double costo, LocalDate fecha, DataTipoCobro tipoCobro, Vehiculo vehiculo) {
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
