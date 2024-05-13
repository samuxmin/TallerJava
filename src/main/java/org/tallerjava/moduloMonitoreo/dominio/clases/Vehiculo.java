package org.tallerjava.moduloMonitoreo.dominio.clases;

import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
    private List<PasadaPorPeaje> pasadasPorPeaje;
    private Vinculo vinculo;
    public Vehiculo(List<PasadaPorPeaje> pasadasPorPeaje, Vinculo v) {
        this.pasadasPorPeaje = pasadasPorPeaje;
        this.vinculo = v;
    }

    public Vehiculo() {
        this.pasadasPorPeaje = new ArrayList<PasadaPorPeaje>();
    }

    public List<PasadaPorPeaje> getPasadasPorPeaje() {
        return pasadasPorPeaje;
    }

    public void setPasadasPorPeaje(List<PasadaPorPeaje> pasadasPorPeaje) {
        this.pasadasPorPeaje = pasadasPorPeaje;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }
}
