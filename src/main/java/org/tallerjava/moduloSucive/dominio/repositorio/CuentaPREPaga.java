package org.tallerjava.moduloSucive.dominio.repositorio;

import java.time.LocalDate;

public class CuentaPREPaga extends Cuenta {
    private float saldo;
    public CuentaPREPaga() {
    }

    public CuentaPREPaga(LocalDate fechaApertura, int nroCuenta, float saldo) {
        super(fechaApertura, nroCuenta);
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
