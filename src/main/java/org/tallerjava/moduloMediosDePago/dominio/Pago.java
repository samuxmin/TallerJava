package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

public class Pago {
    private Usuario usuario;
    private Vehiculo vehiculo;
    private double importe;
    private Tarjeta tarjeta;
    private LocalDate fecha;

    public Pago(Usuario usuario, Vehiculo vehiculo, double importe, Tarjeta tarjeta, LocalDate fecha) {
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.importe = importe;
        this.tarjeta = tarjeta;
        this.fecha = LocalDate.now(); // Asumimos que la fecha del pago es la actual
    }

    public Pago() {
    }
  
    public Usuario getUsuario() {
        return usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getImporte() {
        return importe;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}