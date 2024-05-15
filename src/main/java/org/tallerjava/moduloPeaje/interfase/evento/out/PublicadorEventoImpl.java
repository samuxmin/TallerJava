package org.tallerjava.moduloPeaje.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEventoImpl implements PublicadorEvento{
    @Inject
    private Event<PeajeVehiculoNoEncontrado> vehiculoNoEncontrado;
    @Inject
    private Event<PasajeVehiculo> pasajeVehiculo;
    @Override
    public void publicarVehiculoNoEncontrado(String mensaje){
        vehiculoNoEncontrado.fire(new PeajeVehiculoNoEncontrado(mensaje));
    }
    @Override
    public void publicarPasajeVehiculo(String mensaje){
        pasajeVehiculo.fire(new PasajeVehiculo(mensaje));
    }
}
