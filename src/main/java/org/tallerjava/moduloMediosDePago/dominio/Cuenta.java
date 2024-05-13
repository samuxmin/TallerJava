package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

public class Cuenta {
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
