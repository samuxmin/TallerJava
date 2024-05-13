package org.tallerjava.modulosucive.aplicacion;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;


import org.tallerjava.modulosucive.dominio.repositorio.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SuciveServiceImpl implements SuciveService{
    
    private List<Usuario> usr=new ArrayList<>();
    @PostConstruct
    private void inicializar() {
    }
    
    @Override
    public void notificarPago(String matricula,double importe){
        for(Usuario usuario:usr){
            for(Vinculo vinculo: usuario.getVinculosVehiculos()){
                Vehiculo vehiculo = vinculo.getVehiculo();
                if (vehiculo instanceof VehiculoNacional) {
                    VehiculoNacional vehiculoNacional = (VehiculoNacional) vehiculo;
                    Matricula matriculaVehiculo = vehiculoNacional.getMatricula();
                    if(matriculaVehiculo != null && matriculaVehiculo.getNroMatricula().equals(matricula)){
                        LocalDate fechaActual = LocalDate.now();
                        double costo =importe;
                        DataTipoCobro.Tipo tipoCobro = DataTipoCobro.Tipo.SUCIVE;
                        
                        PasadaPorPeaje pasada = new PasadaPorPeaje(costo, fechaActual, tipoCobro, vehiculo);
                        List<PasadaPorPeaje> pas=vehiculo.getPasadasPorPeaje();        
                        pas.add(pasada);
                        vehiculo.setPasadasPorPeaje(pas);
                    }          
                }
            }
        }
    }

    @Override
    public List<PasadaPorPeaje> consultaDePagos(String matricula) {
        List<PasadaPorPeaje> pasadas = new ArrayList<>();
        for (Usuario usuario : usr) {
            for (Vinculo vinculo : usuario.getVinculosVehiculos()) {
                Vehiculo vehiculo = vinculo.getVehiculo();
                if (vehiculo instanceof VehiculoNacional) {
                    VehiculoNacional vehiculoNacional = (VehiculoNacional) vehiculo;
                    Matricula matriculaVehiculo = vehiculoNacional.getMatricula();
                    if (matriculaVehiculo != null && matriculaVehiculo.getNroMatricula().equals(matricula)) {
                        List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
                        for (PasadaPorPeaje pasada : pasadasVehiculo) {
                            if(pasada.getTipoCobro()==DataTipoCobro.Tipo.SUCIVE){
                                pasadas.add(pasada);
                            }
                        }
                        return pasadas;
                    }
                }
            }
        }
        return pasadas;
    }
}

    /* 
    public List<PasadaPorPeaje>consultaDePagos(RangoFechas rangoFechas){
        List<PasadaPorPeaje> pasadas = new ArrayList<>();
        List<Vehiculo> vehiculos = mostraVehiculosVinculados(usuario);
        for(Vehiculo vehiculo : vehiculos){
            List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
            for(PasadaPorPeaje pasada :pasadasVehiculo){
                if(pasada.getFecha().isAfter(fechaInicio) && pasada.getFecha().isBefore(fechaFin)  ){
                    pasadas.add(pasada);
                }
            }
        }
        return pasadas;
    }
    */

/* 
****************************************************
public List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> consultarPasadas(moduloGestionCliente.dominio.clases.Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin){
    List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> pasadas = new ArrayList<>();
    List<moduloGestionCliente.dominio.clases.Vehiculo> vehiculos = mostraVehiculosVinculados(usuario);
    for(moduloGestionCliente.dominio.clases.Vehiculo vehiculo : vehiculos){
        List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
        for(moduloGestionCliente.dominio.clases.PasadaPorPeaje pasada :pasadasVehiculo){
            if(pasada.getFecha().isAfter(fechaInicio) && pasada.getFecha().isBefore(fechaFin)  ){
                pasadas.add(pasada);
            }
        }
    }
    return pasadas;
}


public List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> consultarPasadas(moduloGestionCliente.dominio.clases.Usuario usuario, moduloGestionCliente.dominio.clases.Vehiculo vehiculo , LocalDate fechaInicio, LocalDate fechaFin){
    List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> pasadas = new ArrayList<>();
    if( !(mostraVehiculosVinculados(usuario).contains(vehiculo)) ){
        throw new RuntimeException("Vehiculo no encontrado para ese usuario");
    }
    List<moduloGestionCliente.dominio.clases.PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();


    for(moduloGestionCliente.dominio.clases.PasadaPorPeaje pasada :pasadasVehiculo){
        if(pasada.getFecha().isAfter(fechaInicio) && pasada.getFecha().isBefore(fechaFin)  ){
            pasadas.add(pasada);
        }
    }
    return pasadas;
}
*/