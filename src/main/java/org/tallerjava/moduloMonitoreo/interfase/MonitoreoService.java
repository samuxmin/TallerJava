package org.tallerjava.moduloMonitoreo.interfase;


import org.tallerjava.moduloGestionCliente.dominio.eventos.SaldoInsuficiente;
import org.tallerjava.moduloPeaje.interfase.evento.out.PasajeVehiculo;

public interface MonitoreoService {


    public void notificarPasajeVehiculo(PasajeVehiculo pasajeVehiculo);

    public void notificarCobroSucive();

    public void notificarCobroTarjeta();

    public void notificarCobroTarjetaRechazado();

    public void notificarSaldoInsuficiete(SaldoInsuficiente si);
}