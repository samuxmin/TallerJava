package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

public class CuentaPOSTPaga extends Cuenta{
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

    /* 
    //TODO PREGUNTARLE AL PROFE SI LA TARJETA TIENE SALDO.

    public void agregarPago(PasadaPorPeaje pasada){

        if (tarjeta.getSaldo() >= pasada.getCosto()){

            tarjeta.setSaldo(tarjeta.getSaldo() - pasada.getCosto());
            pasada.setPagada(true); //PROBANDO COSAS 1
            
        }
    }
    */

}
