package org.tallerjava.moduloMediosDePago.interfase.evento.out;

import lombok.Getter;

@Getter
public class CobroTarjeta {
    String ciUsr;
    long nroTarjeta;
    double importe;

    public CobroTarjeta(String ciUsr, long nroTarjeta, double importe) {
        this.ciUsr = ciUsr;
        this.nroTarjeta = nroTarjeta;
        this.importe = importe;
    }
}
