package org.tallerjava.moduloMediosDePago.dominio;

public class Tarifa {
    private int monto;

    public Tarifa(int monto) {
        this.monto = monto;
    }

    public Tarifa() {
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}