package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity(name="CuentaPREPagaPago")
public class CuentaPREPaga extends Cuenta {
    private double saldo;
    public CuentaPREPaga() {
    }

    public CuentaPREPaga(LocalDate fechaApertura, int nroCuenta, double saldo) {
        super(fechaApertura, nroCuenta);
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
