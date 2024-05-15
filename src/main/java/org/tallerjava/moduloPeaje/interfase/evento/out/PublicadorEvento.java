package org.tallerjava.moduloPeaje.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

public interface PublicadorEvento {

    public void publicarVehiculoNoEncontrado(String mensaje);
    public void publicarPasajeVehiculo(String mensaje);
}
