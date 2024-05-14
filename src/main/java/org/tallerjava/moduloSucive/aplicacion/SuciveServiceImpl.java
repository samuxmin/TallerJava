package org.tallerjava.moduloSucive.aplicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.tallerjava.moduloSucive.dominio.repositorio.*;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SuciveServiceImpl implements SuciveService {

    @Inject
    @Getter
    @Setter
    private SuciveRepositorio repo;

    @PostConstruct
    private void inicializar() {

    }

    @Override
    public boolean notificarPago(String matricula, double importe) {
        Usuario usuario = repo.buscarUsuarioPorMatricula(matricula);
        if (usuario != null) {
            repo.crearPasadaPorPeaje(usuario, importe);
            return true;
        }
        return false;
    }

    @Override
    public List<PasadaPorPeaje> consultaDePagos(String matricula) {
        List<PasadaPorPeaje> pasadas = new ArrayList<>();
        Usuario usuario = repo.buscarUsuarioPorMatricula(matricula);
        if (usuario != null) {
            for (Vinculo vinculo : usuario.getVinculosVehiculos()) {
                Vehiculo vehiculo = vinculo.getVehiculo();
                pasadas.addAll(repo.obtenerPasadasPorPeaje(vehiculo));
            }
        }
        return pasadas;
    }

    @Override
    public List<PasadaPorPeaje> consultaDePagos(RangoFechas rangoFechas) {
        List<PasadaPorPeaje> pasadasEnRango = repo.obtenerPasadasEnRango(rangoFechas);
        Map<LocalDate, Double> importesPorDia = repo.calcularImportesPorDia(pasadasEnRango);
        return repo.convertirAMapas(importesPorDia);
    }

}

/*
 * public List<PasadaPorPeaje>consultaDePagos(RangoFechas rangoFechas){
 * List<PasadaPorPeaje> pasadas = new ArrayList<>();
 * List<Vehiculo> vehiculos = mostraVehiculosVinculados(usuario);
 * for(Vehiculo vehiculo : vehiculos){
 * List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
 * for(PasadaPorPeaje pasada :pasadasVehiculo){
 * if(pasada.getFecha().isAfter(fechaInicio) &&
 * pasada.getFecha().isBefore(fechaFin) ){
 * pasadas.add(pasada);
 * }
 * }
 * }
 * return pasadas;
 * }
 */

/*
 ****************************************************
 * public List<moduloGestionCliente.dominio.clases.PasadaPorPeaje>
 * consultarPasadas(moduloGestionCliente.dominio.clases.Usuario usuario,
 * LocalDate fechaInicio, LocalDate fechaFin){
 * List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> pasadas = new
 * ArrayList<>();
 * List<moduloGestionCliente.dominio.clases.Vehiculo> vehiculos =
 * mostraVehiculosVinculados(usuario);
 * for(moduloGestionCliente.dominio.clases.Vehiculo vehiculo : vehiculos){
 * List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> pasadasVehiculo =
 * vehiculo.getPasadasPorPeaje();
 * for(moduloGestionCliente.dominio.clases.PasadaPorPeaje pasada
 * :pasadasVehiculo){
 * if(pasada.getFecha().isAfter(fechaInicio) &&
 * pasada.getFecha().isBefore(fechaFin) ){
 * pasadas.add(pasada);
 * }
 * }
 * }
 * return pasadas;
 * }
 *
 *
 * public List<moduloGestionCliente.dominio.clases.PasadaPorPeaje>
 * consultarPasadas(moduloGestionCliente.dominio.clases.Usuario usuario,
 * moduloGestionCliente.dominio.clases.Vehiculo vehiculo , LocalDate
 * fechaInicio, LocalDate fechaFin){
 * List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> pasadas = new
 * ArrayList<>();
 * if( !(mostraVehiculosVinculados(usuario).contains(vehiculo)) ){
 * throw new RuntimeException("Vehiculo no encontrado para ese usuario");
 * }
 * List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> pasadasVehiculo =
 * vehiculo.getPasadasPorPeaje();
 *
 *
 * for(moduloGestionCliente.dominio.clases.PasadaPorPeaje pasada
 * :pasadasVehiculo){
 * if(pasada.getFecha().isAfter(fechaInicio) &&
 * pasada.getFecha().isBefore(fechaFin) ){
 * pasadas.add(pasada);
 * }
 * }
 * return pasadas;
 * }
 */