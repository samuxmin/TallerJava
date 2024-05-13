package org.tallerjava.moduloMediosDePago.dominio;

import java.time.LocalDate;

public class RangoFechas {

    private LocalDate fechaInicial;
    private LocalDate fechaFinal;

    public RangoFechas(LocalDate fechaInicial,LocalDate fechaFinal) {
        this.fechaInicial=fechaInicial;
        this.fechaFinal=fechaFinal;
    }

    public RangoFechas() {
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
