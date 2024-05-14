package org.tallerjava.moduloPeaje.infraestructura.persistencia;

import org.tallerjava.moduloPeaje.dominio.*;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;

import java.util.ArrayList;

public class PeajeRepositorioImpl implements PeajeRepositorio {
    @Override
    public Vehiculo findByTag(String tag) {
        Vehiculo vehiculo = new VehiculoNacional(new ArrayList<PasadaPorPeaje>(),null,new Matricula("123"), new Tag(123));
        //VehiculoNacional(List<PasadaPorPeaje> pasadasPorPeaje,Vinculo v, Matricula matricula, Tag tag)
        return vehiculo;
    }

    @Override
    public Vehiculo findByMatricula(String matricula) {
        return null;
    }

    @Override
    public TarifaPreferencial obtenerTarifaPreferencial() {
        return new TarifaPreferencial(100);
    }

    @Override
    public TarifaComun obtenerTarifaComun() {
        return new TarifaComun(150);
    }
}
