package org.tallerjava.moduloPeaje.dominio;



public class TarifaPreferencial extends Tarifa{
    public TarifaPreferencial(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor=valor;
    }
}
