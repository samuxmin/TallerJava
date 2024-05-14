package org.tallerjava.moduloMonitoreo.aplicacion;

import jakarta.enterprise.event.Observes;
import org.tallerjava.moduloGestionCliente.dominio.eventos.SaldoInsuficiente;
import org.tallerjava.moduloMonitoreo.interfase.MonitoreoService;
import org.tallerjava.moduloPeaje.interfase.evento.out.PasajeVehiculo;

public class MonitoreoServiceImpl implements MonitoreoService {
    public void notificarPasajeVehiculo(@Observes PasajeVehiculo pasajeVehiculo){
        System.out.println(pasajeVehiculo.getDescripcion());
    }

    public void notificarCobroSucive(){

    }

    public void notificarCobroTarjeta(){

    }

    public void notificarCobroTarjetaRechazado(){

    }

    public void notificarSaldoInsuficiete(@Observes SaldoInsuficiente evento){
        System.out.println(evento);
    }
}
