package org.tallerjava.moduloSucive.aplicacion;

import org.tallerjava.moduloSucive.dominio.repositorio.PasadaPorPeaje;
import org.tallerjava.moduloSucive.dominio.repositorio.RangoFechas;

import java.util.List;


public interface SuciveService {
    public boolean notificarPago(String matricula,double importe);
    public List<PasadaPorPeaje>consultaDePagos(RangoFechas rangoFechas);
    public List<PasadaPorPeaje>consultaDePagos(String matricula);
}
