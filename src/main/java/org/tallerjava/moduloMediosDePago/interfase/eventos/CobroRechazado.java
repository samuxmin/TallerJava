package org.tallerjava.moduloMediosDePago.interfase.eventos;

import lombok.Getter;

@Getter
public class CobroRechazado {
    String ciUsr;
    int nroTarjeta;
    double importe;
    String descripcion;

    public CobroRechazado(String ciUsr, int nroTarjeta, double importe,String descripcion) {
        this.ciUsr = ciUsr;
        this.nroTarjeta = nroTarjeta;
        this.importe = importe;
        this.descripcion = descripcion;
    }
}
