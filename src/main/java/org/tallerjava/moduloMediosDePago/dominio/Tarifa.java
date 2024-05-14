package org.tallerjava.moduloMediosDePago.dominio;

public class Tarifa {
    private double monto;

    public Tarifa(double monto) {
        this.monto = monto;
    }

    public Tarifa() {
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}