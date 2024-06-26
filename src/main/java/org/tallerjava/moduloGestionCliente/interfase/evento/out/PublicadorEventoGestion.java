package org.tallerjava.moduloGestionCliente.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
//import org.tallerjava.moduloGestion.interfase.remota.rest.VehiculoDTO;


@ApplicationScoped
public class PublicadorEventoGestion {
    @Inject
    private Event<GestionPagoCuentaPrePaga> pagoCuentaPrePaga;

    @Inject
    private Event<GestionPagoCuentaPostPaga> pagoCuentaPostPaga;

    @Inject
    private Event<GestionNuevoVehiculo> nuevoVehiculo;

    public void publicarPagoCuentaPrePaga(String mensaje){
        pagoCuentaPrePaga.fire(new GestionPagoCuentaPrePaga(mensaje));
    }

    public void publicarPagoCuentaPostPaga(String mensaje){
        pagoCuentaPostPaga.fire(new GestionPagoCuentaPostPaga(mensaje));
    }

    public void publicarNuevoVehiculo(Vehiculo vehiculo){
        GestionNuevoVehiculo evento = new GestionNuevoVehiculo(
                vehiculo.getTag(),
                vehiculo.getMatricula(),
                vehiculo.getNacionalidad().toString()
        );

        nuevoVehiculo.fire(evento);
    }
}
