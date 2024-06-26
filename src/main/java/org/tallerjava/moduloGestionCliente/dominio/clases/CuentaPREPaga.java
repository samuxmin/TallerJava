package org.tallerjava.moduloGestionCliente.dominio.clases;

import java.time.LocalDate;


public class CuentaPREPaga extends Cuenta {

    private double saldo;
    public CuentaPREPaga() {
    }

    public CuentaPREPaga(LocalDate fechaApertura, int nroCuenta, float saldo) {
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
