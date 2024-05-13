package org.tallerjava.moduloMonitoreo.interfase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import org.tallerjava.moduloMonitoreo.aplicacion.CobroSucive;
import org.tallerjava.moduloMonitoreo.aplicacion.CobroTarjeta;
import org.tallerjava.moduloMonitoreo.aplicacion.PasajeVehiculo;
import org.tallerjava.moduloGestionCliente.dominio.eventos.SaldoInsuficiente;

@ApplicationScoped
public class MonitoreoService {

    @Inject
    private Event<PasajeVehiculo> pasajeVehiculoEvent;
    private Event<CobroSucive> cobroSuciveEvent;
    private Event<CobroTarjeta> cobroTarjetaEvent;
    private Event<CobroTarjeta> cobroTarjetaRechazadoEvent;
    private Event<SaldoInsuficiente> saldoInsuficienteEvent;


    public void notificarPasajeVehiculo (PasajeVehiculo pasajeVehiculo) {

        pasajeVehiculoEvent.fire(pasajeVehiculo);
    }
    public void notificarCobroSucive (CobroSucive cobroSucive) {
        cobroSuciveEvent.fire(cobroSucive);
    }

    public void notificarCobroTarjeta (CobroTarjeta cobroTarjeta) {
        cobroTarjetaEvent.fire(cobroTarjeta);
    }

    public void notificarCobroTarjetaRechazado (CobroTarjeta cobroTarjeta) {
        cobroTarjetaRechazadoEvent.fire(cobroTarjeta);
    }

    public void notificarSaldoInsuficiete(SaldoInsuficiente si){
        saldoInsuficienteEvent.fire(si);
    }
}
