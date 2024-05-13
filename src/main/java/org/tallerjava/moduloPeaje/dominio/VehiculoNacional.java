package org.tallerjava.moduloPeaje.dominio;

import java.util.List;

public class VehiculoNacional extends Vehiculo {
    private Tag tag;
    private Matricula matricula;

    public VehiculoNacional() {}


    public VehiculoNacional(List<PasadaPorPeaje> pasadasPorPeaje,Vinculo v, Matricula matricula, Tag tag) {
        super(pasadasPorPeaje, v);
        this.matricula = matricula;
        this.tag = tag;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}
