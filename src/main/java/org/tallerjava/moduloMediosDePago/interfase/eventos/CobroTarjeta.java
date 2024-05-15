package org.tallerjava.moduloMediosDePago.interfase.eventos;

import lombok.Getter;

@Getter
public class CobroTarjeta {
    String ciUsr;
    int nroTarjeta;
    double importe;

    public CobroTarjeta(String ciUsr, int nroTarjeta, double importe) {
        this.ciUsr = ciUsr;
        this.nroTarjeta = nroTarjeta;
        this.importe = importe;
    }
}
