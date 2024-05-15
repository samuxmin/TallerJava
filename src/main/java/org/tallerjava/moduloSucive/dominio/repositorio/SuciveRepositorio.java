package org.tallerjava.moduloSucive.dominio.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface SuciveRepositorio {
    public Vehiculo findByMatricula(String matricula);
    public Usuario buscarUsuarioPorMatricula(String matricula);
    public void crearPasadaPorPeaje(Usuario usuario, double importe);
    public List<PasadaPorPeaje> obtenerPasadasPorPeaje(Vehiculo vehiculo);
    public List<PasadaPorPeaje> obtenerPasadasEnRango(RangoFechas rangoFechas);
    public Map<LocalDate, Double> calcularImportesPorDia(List<PasadaPorPeaje> pasadasPorPeaje);
    public List<PasadaPorPeaje> convertirAMapas(Map<LocalDate, Double> importesPorDia);
    public void addUsuario(Usuario u);
}
