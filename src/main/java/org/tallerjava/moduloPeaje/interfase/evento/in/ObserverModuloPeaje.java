package org.tallerjava.moduloPeaje.interfase.evento.in;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestionCliente.interfase.evento.out.GestionNuevoVehiculo;
import org.tallerjava.moduloPeaje.aplicacion.PeajeService;
import org.tallerjava.moduloPeaje.dominio.Nacionalidad;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObserverModuloPeaje {
    private static final Logger log = Logger.getLogger(ObserverModuloPeaje.class);

    @Inject
    private PeajeService servicioPeaje;

    public void accept(@Observes GestionNuevoVehiculo event) {
        log.infof("Evento procesado: GestionNuevoVehiculo: %s", event.toString());
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTag(event.getTag());
        vehiculo.setMatricula(event.getMatricula());
        vehiculo.setNacionalidad(Nacionalidad.valueOf(event.getNacionalidad()));


        servicioPeaje.addVehiculo(vehiculo);
    }

}
