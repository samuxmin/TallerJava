package org.tallerjava.moduloPeaje.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {
    @Inject
    private Event<PeajeVehiculoNacional> vehiculoNacional;
    
    @Inject
    private Event<PeajeVehiculoExtranjero> vehiculoExtranjero;

    @Inject
    private Event<PeajePagoSucive> pagoSuciveEvento;

    @Inject
    private Event<PeajeVehiculoNoEncontrado> vehiculoNoEncontradoEvento;
    

    public void publicarVehiculoNacional(String mensaje){
        vehiculoNacional.fire(new PeajeVehiculoNacional(mensaje));
    }

    public void publicarVehiculoExtranjero(String mensaje){
        vehiculoExtranjero.fire(new PeajeVehiculoExtranjero(mensaje));
    }

    public void publicarPagoSucive(String mensaje){
        pagoSuciveEvento.fire(new PeajePagoSucive(mensaje));
    }

    public void publicarVehiculoNoEncontrado(String mensaje){
        vehiculoNoEncontradoEvento.fire(new PeajeVehiculoNoEncontrado(mensaje));
    }
}
