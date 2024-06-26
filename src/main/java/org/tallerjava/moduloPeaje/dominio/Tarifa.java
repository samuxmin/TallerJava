package org.tallerjava.moduloPeaje.dominio;

public abstract class Tarifa {
    protected double valor;

    public Tarifa(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}