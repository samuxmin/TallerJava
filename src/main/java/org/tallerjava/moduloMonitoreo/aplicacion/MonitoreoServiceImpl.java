package org.tallerjava.moduloMonitoreo.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.tallerjava.moduloGestionCliente.dominio.eventos.SaldoInsuficiente;
import org.tallerjava.moduloMediosDePago.interfase.eventos.CobroRechazado;
import org.tallerjava.moduloMediosDePago.interfase.eventos.CobroTarjeta;
import org.tallerjava.moduloMonitoreo.interfase.MonitoreoService;
import org.tallerjava.moduloPeaje.interfase.evento.out.PasajeVehiculo;
import org.tallerjava.moduloSucive.interfase.eventos.PagoSucive;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MonitoreoServiceImpl implements MonitoreoService {
    List<PasajeVehiculo> pasajeVehiculoList = new ArrayList<>();
    List<PagoSucive> pagoSuciveList = new ArrayList<>();
    List<CobroRechazado> cobroRechazadoList = new ArrayList<>();
    List<CobroTarjeta> cobroTarjetaList = new ArrayList<>();
    List<SaldoInsuficiente> saldoInsuficienteList = new ArrayList<>();

    public void notificarPasajeVehiculo(@Observes PasajeVehiculo pasajeVehiculo){
        System.out.println(pasajeVehiculo.getDescripcion());
        pasajeVehiculoList.add(pasajeVehiculo);
    }

    public void notificarCobroSucive(PagoSucive pagoSucive){
        System.out.println("Pago de " + pagoSucive.getImporte() + " para la matricula + " + pagoSucive.getMatricula());
        pagoSuciveList.add(pagoSucive);
    }

    public void notificarCobroTarjeta(@Observes CobroTarjeta cobroTarjeta){
        System.out.println("Cobro realizado: " + cobroTarjeta.getImporte() + " a usuario con ci: " +cobroTarjeta.getCiUsr() + "a nroTarjeta: "+ cobroTarjeta.getNroTarjeta());
        cobroTarjetaList.add(cobroTarjeta);
    }

    public void notificarCobroTarjetaRechazado(@Observes CobroRechazado cobroRechazado){
        System.out.println(cobroRechazado.getDescripcion());
        cobroRechazadoList.add(cobroRechazado);
    }

    public void notificarSaldoInsuficiente(@Observes SaldoInsuficiente evento){
        System.out.println(evento);
        saldoInsuficienteList.add(evento);
    }
}
