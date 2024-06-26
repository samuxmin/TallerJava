package org.tallerjava.moduloGestionCliente.dominio.clases;

import java.time.LocalDate;
public class CuentaPOSTPaga extends Cuenta {

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
