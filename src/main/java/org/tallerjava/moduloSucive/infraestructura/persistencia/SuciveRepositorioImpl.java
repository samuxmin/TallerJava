package org.tallerjava.moduloSucive.infraestructura.persistencia;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tallerjava.moduloSucive.dominio.repositorio.DataTipoCobro;
import org.tallerjava.moduloSucive.dominio.repositorio.Matricula;
import org.tallerjava.moduloSucive.dominio.repositorio.PasadaPorPeaje;
import org.tallerjava.moduloSucive.dominio.repositorio.RangoFechas;
import org.tallerjava.moduloSucive.dominio.repositorio.SuciveRepositorio;
import org.tallerjava.moduloSucive.dominio.repositorio.Usuario;
import org.tallerjava.moduloSucive.dominio.repositorio.Vehiculo;
import org.tallerjava.moduloSucive.dominio.repositorio.VehiculoNacional;
import org.tallerjava.moduloSucive.dominio.repositorio.Vinculo;

public class SuciveRepositorioImpl implements SuciveRepositorio {

    public List<Usuario> usr;
    public DataTipoCobro.Tipo tipoCobro;
    public Matricula matricula;
    public LocalDate fecha;
    public Vehiculo vehiculo;
    public PasadaPorPeaje pasadaPorPeaje;
    public List<PasadaPorPeaje> pasadasPorPeaje;
    public Vinculo vinculo;
    public List<Vinculo> vinculosVehiculos;
    public Usuario usuario;
    public VehiculoNacional vehiculoNacional;
    public RangoFechas rangoFechas;

    public SuciveRepositorioImpl() {
        this.usr = new ArrayList<>();
        this.tipoCobro = DataTipoCobro.Tipo.SUCIVE;
        this.matricula = new Matricula("ABCD");
        this.fecha = LocalDate.now();
        this.vehiculo = new Vehiculo(new ArrayList<>(), new Vinculo(true, fecha, null));
        this.pasadaPorPeaje = new PasadaPorPeaje(10.5, fecha, tipoCobro, vehiculo);
        this.pasadasPorPeaje = new ArrayList<>();
        this.pasadasPorPeaje.add(pasadaPorPeaje);
        this.vinculo = new Vinculo(true, fecha, vehiculo);
        this.vinculosVehiculos = new ArrayList<>();
        this.vinculosVehiculos.add(vinculo);
        this.usuario = new Usuario("correo@example.com", "Facundo", "12345678", vinculosVehiculos, null);
        this.vehiculoNacional = new VehiculoNacional(pasadasPorPeaje, vinculo, matricula, null);
        this.rangoFechas = new RangoFechas(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
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