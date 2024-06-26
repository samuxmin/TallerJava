package org.tallerjava.moduloSucive.infraestructura.persistencia;

import java.util.List;

import org.tallerjava.moduloSucive.dominio.repositorio.PasadaPorPeaje;
import org.tallerjava.moduloSucive.dominio.repositorio.RangoFechas;
import org.tallerjava.moduloSucive.dominio.repositorio.Vehiculo;

public interface SuciveRepositorio {
    //public Vehiculo findByMatricula(String matricula);
    public List<PasadaPorPeaje> obtenerPasadasPorPeaje(Vehiculo vehiculo);
    public List<PasadaPorPeaje> obtenerPasadasEnRango(RangoFechas rangoFechas);
    //public void persistUsr(Usuario u);
    public boolean crearPasadaPorPeaje(String matricula, double importe);
    public Vehiculo buscarVehiculoPorMatricula(String matricula);
    public void addVehiculo(Vehiculo v);
}
