package org.tallerjava.moduloSucive.dominio.repositorio;

import java.time.LocalDate;


public class RangoFechas {
    private LocalDate fechainicial;
    private LocalDate fechafinal;
    
    public RangoFechas(LocalDate fechaInicial,LocalDate fechaFinal){
        this.fechafinal=fechaFinal;
        this.fechainicial=fechaInicial;
    }

    public RangoFechas(){

    }
    public LocalDate getFechainicial() {
        return this.fechainicial;
    }

    public void setFechainicial(LocalDate fechainicial) {
        this.fechainicial = fechainicial;
    }

    public LocalDate getFechafinal() {
        return this.fechafinal;
    }

    public void setFechafinal(LocalDate fechafinal) {
        this.fechafinal = fechafinal;
    }

}
