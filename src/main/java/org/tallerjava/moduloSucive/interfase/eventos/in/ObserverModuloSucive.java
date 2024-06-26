package org.tallerjava.moduloSucive.interfase.eventos.in;

import org.tallerjava.moduloGestionCliente.interfase.evento.out.GestionNuevoVehiculo;
import org.tallerjava.moduloSucive.aplicacion.SuciveService;
import org.tallerjava.moduloSucive.dominio.repositorio.Vehiculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObserverModuloSucive {

    @Inject
    private SuciveService servicio;

    public void accept(@Observes GestionNuevoVehiculo event) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTag(event.getTag());
        vehiculo.setMatricula(event.getMatricula());
        System.out.println();System.out.println();System.out.println(event.getNacionalidad());

        if(event.getNacionalidad().equals("NACIONAL")){
            servicio.addVehiculo(vehiculo);
        }
    }

}
