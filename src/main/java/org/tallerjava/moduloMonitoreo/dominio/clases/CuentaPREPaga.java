package org.tallerjava.moduloMonitoreo.dominio.clases;

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

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
