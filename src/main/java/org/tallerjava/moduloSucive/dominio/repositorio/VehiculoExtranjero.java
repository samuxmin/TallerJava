package org.tallerjava.moduloSucive.dominio.repositorio;

import java.util.List;

public class VehiculoExtranjero extends Vehiculo {
    private Tag tag;

    public VehiculoExtranjero(List<PasadaPorPeaje> p,Vinculo v, Tag tag) {
        super(p,v);
        this.tag = tag;
    }

    public VehiculoExtranjero() {}

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
