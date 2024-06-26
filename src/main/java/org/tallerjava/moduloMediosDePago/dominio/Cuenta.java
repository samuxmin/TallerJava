package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name="CuentaPago")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nroCuenta;
    private LocalDate fechaApertura;

    public Cuenta() {
    }

    public Cuenta(LocalDate fechaApertura, int nroCuenta) {
        this.fechaApertura = fechaApertura;
        this.nroCuenta = nroCuenta;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }
}
