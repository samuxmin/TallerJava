package org.tallerjava.moduloPeaje.infraestructura.persistencia;

import jakarta.enterprise.context.ApplicationScoped;
import org.tallerjava.moduloPeaje.dominio.*;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PeajeRepositorioImpl implements PeajeRepositorio {
    private List<Vehiculo> vehiculos = new ArrayList<>();
    @Override
    public Vehiculo findByTag(String tag) {

        for(Vehiculo vehiculo : vehiculos) {
            if(vehiculo.getTag().getId().equals(tag)) {
                return vehiculo;
            }
        }
        return null;
        //Vehiculo vehiculo = new VehiculoNacional(new ArrayList<PasadaPorPeaje>(),null,new Matricula("123"), new Tag("123"));
        //VehiculoNacional(List<PasadaPorPeaje> pasadasPorPeaje,Vinculo v, Matricula matricula, Tag tag)
        //return vehiculo;

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

    @Override
    public void addVehiculo(Vehiculo v){
        vehiculos.add(v);
    }
}
