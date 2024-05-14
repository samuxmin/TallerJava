package org.tallerjava.moduloPeaje.dominio;


public class TarifaComun extends Tarifa{
    public TarifaComun(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor=valor;
    }
}
