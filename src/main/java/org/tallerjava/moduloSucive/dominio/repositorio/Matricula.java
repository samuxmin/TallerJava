package org.tallerjava.moduloSucive.dominio.repositorio;

public class Matricula {
    private String nroMatricula;

    public Matricula(String nroMatricula) {
        this.nroMatricula = nroMatricula;
    }

    public Matricula() {
    }
    public String getNroMatricula() {
        return nroMatricula;
    }
    public void setNroMatricula(String nroMatricula) {
        this.nroMatricula = nroMatricula;
    }
}
