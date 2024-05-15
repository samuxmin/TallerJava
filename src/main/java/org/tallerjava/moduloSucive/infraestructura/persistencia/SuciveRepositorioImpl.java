package org.tallerjava.moduloSucive.infraestructura.persistencia;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.tallerjava.moduloGestionCliente.dominio.eventos.VinculoEvent;
import org.tallerjava.moduloSucive.dominio.repositorio.*;

@ApplicationScoped

public class SuciveRepositorioImpl implements SuciveRepositorio {

    private List<Usuario> usr = new ArrayList<>();


    public SuciveRepositorioImpl() {

    }

    public void addUsuario(@Observes VinculoEvent vinculoEvent){
        if(vinculoEvent.getTipo().equals("nacional")){
            Usuario usuario = new Usuario();
            usuario.setCi(vinculoEvent.getCiUSr());

            VehiculoNacional vehiculo = new VehiculoNacional();
            vehiculo.setTag(new Tag(vinculoEvent.getTag()));
            vehiculo.setMatricula(new Matricula(vinculoEvent.getMatricula()));

            Vinculo vinculo = new Vinculo();
            vinculo.setActivo(true);
            vinculo.setVehiculo(vehiculo);
            vinculo.setFechaIni(LocalDate.now());

            usuario.getVinculosVehiculos().add(vinculo);

            usr.add(usuario);

        }
    }

    @Override
    public Vehiculo findByMatricula(String matricula) {
        return null;
    }

    /***************************************************************************************** */
    @Override
    public Usuario buscarUsuarioPorMatricula(String matricula) {
        for (Usuario usuario : usr) {
            for (Vinculo vinculo : usuario.getVinculosVehiculos()) {
                Vehiculo vehiculo = vinculo.getVehiculo();
                if (vehiculo instanceof VehiculoNacional) {
                    VehiculoNacional vehiculoNacional = (VehiculoNacional) vehiculo;
                    Matricula matriculaVehiculo = vehiculoNacional.getMatricula();
                    if (matriculaVehiculo != null && matriculaVehiculo.getNroMatricula().equals(matricula)) {
                        return usuario;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void crearPasadaPorPeaje(Usuario usuario, double importe) {
        LocalDate fecha = LocalDate.now();
        double costo = importe;
        DataTipoCobro.Tipo tipoCobro = DataTipoCobro.Tipo.SUCIVE;

        for (Vinculo vinculo : usuario.getVinculosVehiculos()) {
            Vehiculo vehiculo = vinculo.getVehiculo();
            if (vehiculo instanceof VehiculoNacional) {
                PasadaPorPeaje pasada = new PasadaPorPeaje(costo, fecha, tipoCobro, vehiculo);
                List<PasadaPorPeaje> pas = vehiculo.getPasadasPorPeaje();
                pas.add(pasada);
                vehiculo.setPasadasPorPeaje(pas);
            }
        }
    }

    @Override
    public List<PasadaPorPeaje> obtenerPasadasPorPeaje(Vehiculo vehiculo) {
        List<PasadaPorPeaje> pasadas = new ArrayList<>();
        if (vehiculo instanceof VehiculoNacional) {
            List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
            for (PasadaPorPeaje pasada : pasadasVehiculo) {
                if (pasada.getTipoCobro() == DataTipoCobro.Tipo.SUCIVE) {
                    pasadas.add(pasada);
                }
            }
        }
        return pasadas;
    }

    /***************************************************************************************** */

    public List<PasadaPorPeaje> obtenerPasadasEnRango(RangoFechas rangoFechas) {
        List<PasadaPorPeaje> pasadasEnRango = new ArrayList<>();
        for (Usuario usuario : usr) {
            for (Vinculo vinculo : usuario.getVinculosVehiculos()) {
                Vehiculo vehiculo = vinculo.getVehiculo();
                List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
                for (PasadaPorPeaje pasada : pasadasVehiculo) {
                    LocalDate fechaPasada = pasada.getFecha();
                    if (fechaPasada.isAfter(rangoFechas.getFechainicial()) && fechaPasada.isBefore(rangoFechas.getFechafinal())) {
                        pasadasEnRango.add(pasada);
                    }
                }
            }
        }
        return pasadasEnRango;
    }

    public Map<LocalDate, Double> calcularImportesPorDia(List<PasadaPorPeaje> pasadasPorPeaje) {
        Map<LocalDate, Double> importesPorDia = new HashMap<>();
        for (PasadaPorPeaje pasada : pasadasPorPeaje) {
            LocalDate fechaPasada = pasada.getFecha();
            double costoPasada = pasada.getCosto();
            importesPorDia.put(fechaPasada, importesPorDia.getOrDefault(fechaPasada, 0.0) + costoPasada);
        }
        return importesPorDia;
    }

    public List<PasadaPorPeaje> convertirAMapas(Map<LocalDate, Double> importesPorDia) {
        List<PasadaPorPeaje> importesPorDiaList = new ArrayList<>();
        for (Map.Entry<LocalDate, Double> entry : importesPorDia.entrySet()) {
            LocalDate fecha = entry.getKey();
            double importeTotal = entry.getValue();
            importesPorDiaList.add(new PasadaPorPeaje(importeTotal, fecha, null, null));
        }
        return importesPorDiaList;
    }


}

/*
 * public void notificarPago(String matricula,double importe){
 *
 * for(Usuario usuario:usr){
 * for(Vinculo vinculo: usuario.getVinculosVehiculos()){
 * Vehiculo vehiculo = vinculo.getVehiculo();
 * if (vehiculo instanceof VehiculoNacional) {
 * VehiculoNacional vehiculoNacional = (VehiculoNacional) vehiculo;
 * Matricula matriculaVehiculo = vehiculoNacional.getMatricula();
 * if(matriculaVehiculo != null &&
 * matriculaVehiculo.getNroMatricula().equals(matricula)){
 * LocalDate fechaActual = LocalDate.now();
 * double costo =importe;
 * DataTipoCobro.Tipo tipoCobro = DataTipoCobro.Tipo.SUCIVE;
 *
 * PasadaPorPeaje pasada = new PasadaPorPeaje(costo, fechaActual, tipoCobro,
 * vehiculo);
 * List<PasadaPorPeaje> pas=vehiculo.getPasadasPorPeaje();
 * pas.add(pasada);
 * vehiculo.setPasadasPorPeaje(pas);
 * }
 * }
 * }
 * }
 * }
 */