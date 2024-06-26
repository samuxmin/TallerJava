package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name="CuentaPOSTPaga")
public class CuentaPOSTPaga extends Cuenta{

    @OneToOne
    private Tarjeta tarjeta;


    public CuentaPOSTPaga() {

    }

    public CuentaPOSTPaga(LocalDate fechaApertura, int nroCuenta, Tarjeta tarjeta) {
        super(fechaApertura, nroCuenta);
        this.tarjeta = tarjeta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }



}
