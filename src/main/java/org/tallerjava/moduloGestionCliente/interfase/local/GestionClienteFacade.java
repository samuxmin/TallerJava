package org.tallerjava.moduloGestionCliente.interfase.local;

import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteService;
import org.tallerjava.moduloGestionCliente.dominio.clases.DataTipoCobroGestion;
import org.tallerjava.moduloGestionCliente.dominio.clases.PasadaPorPeaje;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloPeaje.dominio.PasadaPorPeajePeaje;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GestionClienteFacade {

    @Inject
    GestionClienteService service;
    public void registrarPasadaPorPeaje(PasadaPorPeajePeaje pasada) {

        Vehiculo vehiculo = service.getVehiculoByTag(pasada.getVehiculo().getTag());
        PasadaPorPeaje pasadaGestion = new PasadaPorPeaje();
        pasadaGestion.setCosto(pasada.getCosto());
        pasadaGestion.setFecha(pasada.getFecha());
        pasadaGestion.setVehiculo(vehiculo);
        switch (pasada.getTipoCobro()) {
            case prePago:
                pasadaGestion.setTipoCobro(DataTipoCobroGestion.prePago);
                break;
            case postPago:
                pasadaGestion.setTipoCobro(DataTipoCobroGestion.postPago);
                break;
            case sucive:
                pasadaGestion.setTipoCobro(DataTipoCobroGestion.sucive);
                break;
        }
        service.addPasada(pasadaGestion);
    }

    
}