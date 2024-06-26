package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="PagoPago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    @JsonbTransient
    private Vehiculo vehiculo;
    private double importe;
    @ManyToOne
    private Tarjeta tarjeta;
    private LocalDate fecha;

    public Pago(Usuario usuario, Vehiculo vehiculo, double importe, Tarjeta tarjeta, LocalDate fecha) {
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.importe = importe;
        this.tarjeta = tarjeta;
        this.fecha = fecha; //lo cambie porque estaba en localdatenow
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