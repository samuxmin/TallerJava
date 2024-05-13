package org.tallerjava.modulosucive.aplicacion;

import org.tallerjava.modulosucive.dominio.repositorio.PasadaPorPeaje;

import java.util.List;





public interface SuciveService {
    public void notificarPago(String matricula,double importe);
    //public List<PasadaPorPeaje>consultaDePagos(RangoFechas rangoFechas);
    public List<PasadaPorPeaje>consultaDePagos(String matricula);
}
