package org.tallerjava.moduloSucive.aplicacion;

import java.util.List;

import org.tallerjava.moduloSucive.dominio.repositorio.PasadaPorPeaje;
import org.tallerjava.moduloSucive.dominio.repositorio.RangoFechas;
import org.tallerjava.moduloSucive.dominio.repositorio.Vehiculo;


public interface SuciveService {
    public boolean notificarPago(String matricula,double importe);
    public List<PasadaPorPeaje>consultaDePagos(RangoFechas rangoFechas);
    public List<PasadaPorPeaje>consultaDePagos(String matricula);
    void addVehiculo(Vehiculo v);
    
}
