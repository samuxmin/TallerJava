package org.tallerjava.moduloSucive.interfase.api;

public class PagoRequest {
    private int monto;
    private String matricula;

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
