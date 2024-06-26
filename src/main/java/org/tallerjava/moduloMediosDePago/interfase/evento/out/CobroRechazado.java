package org.tallerjava.moduloMediosDePago.interfase.evento.out;

import lombok.Getter;

@Getter
public class CobroRechazado {
    String ciUsr;
    long nroTarjeta;
    double importe;
    String descripcion;

    public CobroRechazado(String ciUsr, long nroTarjeta, double importe,String descripcion) {
        this.ciUsr = ciUsr;
        this.nroTarjeta = nroTarjeta;
        this.importe = importe;
        this.descripcion = descripcion;
    }
}
