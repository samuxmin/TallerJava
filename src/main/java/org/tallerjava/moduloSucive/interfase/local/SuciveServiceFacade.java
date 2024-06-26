package org.tallerjava.moduloSucive.interfase.local;

import java.util.List;

import org.tallerjava.moduloSucive.aplicacion.SuciveService;
import org.tallerjava.moduloSucive.aplicacion.SuciveServiceImpl;
import org.tallerjava.moduloSucive.dominio.repositorio.PasadaPorPeaje;
import org.tallerjava.moduloSucive.dominio.repositorio.RangoFechas;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped

public class SuciveServiceFacade {
    @Inject
    private SuciveService serviciosucive;


    public boolean notificarPago(String matricula,double importe){
        return serviciosucive.notificarPago(matricula,importe);
    }

    public List<PasadaPorPeaje>consultaDePagos(RangoFechas rangoFechas){
        return serviciosucive.consultaDePagos(rangoFechas);
    }

    public List<PasadaPorPeaje>consultaDePagos(String matricula){
        return serviciosucive.consultaDePagos(matricula);
    }
}
