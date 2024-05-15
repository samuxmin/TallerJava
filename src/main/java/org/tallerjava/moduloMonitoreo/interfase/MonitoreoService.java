package org.tallerjava.moduloMonitoreo.interfase;


import org.tallerjava.moduloGestionCliente.dominio.eventos.SaldoInsuficiente;
import org.tallerjava.moduloMediosDePago.interfase.eventos.CobroRechazado;
import org.tallerjava.moduloMediosDePago.interfase.eventos.CobroTarjeta;
import org.tallerjava.moduloPeaje.interfase.evento.out.PasajeVehiculo;
import org.tallerjava.moduloSucive.interfase.eventos.PagoSucive;

public interface MonitoreoService {


    public void notificarPasajeVehiculo(PasajeVehiculo pasajeVehiculo);

    public void notificarCobroSucive(PagoSucive pagoSucive);

    public void notificarCobroTarjeta(CobroTarjeta cobroTarjeta);

    public void notificarCobroTarjetaRechazado(CobroRechazado cobroRechazado);

    public void notificarSaldoInsuficiente(SaldoInsuficiente si);
}