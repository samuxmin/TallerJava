package org.tallerjava.moduloPeaje.aplicacion;

import java.time.LocalDate;

import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteService;
import org.tallerjava.moduloGestionCliente.interfase.local.GestionClienteFacade;
import org.tallerjava.moduloPeaje.dominio.DataTipoCobro;
import org.tallerjava.moduloPeaje.dominio.Nacionalidad;
import org.tallerjava.moduloPeaje.dominio.PasadaPorPeajePeaje;
import org.tallerjava.moduloPeaje.dominio.TarifaComun;
import org.tallerjava.moduloPeaje.dominio.TarifaPreferencial;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;
import org.tallerjava.moduloPeaje.infraestructura.messaging.EnviarPeajeQueueUtil;
import org.tallerjava.moduloPeaje.infraestructura.messaging.PeajeRealizadoMessage;
import org.tallerjava.moduloPeaje.interfase.evento.out.PublicadorEvento;
import org.tallerjava.moduloSucive.aplicacion.SuciveService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServicioPeajeImpl implements PeajeService {

    TarifaPreferencial tarifaPreferencial = new TarifaPreferencial(100);
    TarifaComun tarifaComun = new TarifaComun(150);

    @Inject
    private PeajeRepositorio repo;

    @Inject
    private PublicadorEvento evento;// = new PublicadorEvento();

    @Inject
    GestionClienteService gestionCliente;

    @Inject
    GestionClienteFacade gestionClienteFacade;

    @Inject
    SuciveService suciveService;
    //private ServicioPagoFacade servicioPagoFacade;

    @Inject
    private EnviarPeajeQueueUtil enviarPeajeQueueUtil;

    @Override
    @Transactional
    public boolean estaHabilitadoSincronico(String tag, String matricula) {

        Vehiculo vehiculo = existeVehiculo(tag, matricula);
        if (vehiculo != null) {

            if (vehiculo.getNacionalidad() == Nacionalidad.NACIONAL) {
                if(vehiculo.getMatricula() == null ){
                    System.out.println("le tapaste la chapa :p");
                    return false;
                }
                PeajeRealizadoMessage pasada = new PeajeRealizadoMessage(tag, matricula);
                String mensaje = pasada.toJson();
                enviarPeajeQueueUtil.enviarMensaje(mensaje);
                return true;

                //return procesarVehiculoNacional(tag, matricula);

            } else {
                return procesarVehiculoExtranjero(tag, vehiculo);
            }
        } else {
            evento.publicarVehiculoNoEncontrado(tag + " " + matricula);
            return false;
        }
    }

    @Transactional
    @Override
    public void addVehiculo(Vehiculo vehiculo) {

        repo.addVehiculo(vehiculo);

    }

    private boolean procesarVehiculoExtranjero(String tag, Vehiculo vehiculo) {
        PasadaPorPeajePeaje pasada = new PasadaPorPeajePeaje();
        pasada.setFecha(LocalDate.now());
        pasada.setVehiculo(vehiculo);
        pasada.setCosto(tarifaPreferencial.getValor()); //tarifa preferencial   
        pasada.setTipoCobro(DataTipoCobro.prePago);
        boolean habilitado = gestionCliente.realizarPrePago(tarifaPreferencial.getValor(), tag);
        //todos los vehiculos extranjeros son preferenciales
        //según las reglas del negocio, lo primero es cobrar con PrePago
        if (!habilitado) {
            habilitado = gestionCliente.realizarPostPago(tarifaPreferencial.getValor(), tag);
            pasada.setTipoCobro(DataTipoCobro.postPago);
        }
        if (habilitado) {
            repo.addPasada(pasada);
            gestionClienteFacade.registrarPasadaPorPeaje(pasada);
            evento.publicarVehiculoExtranjero(tag);
        }

        return habilitado;
    }

    @Override
    @Transactional
    public boolean procesarVehiculoNacional(String tag, String matricula) {
        
        PasadaPorPeajePeaje pasada = new PasadaPorPeajePeaje();
        pasada.setFecha(LocalDate.now());
        
        pasada.setCosto(tarifaPreferencial.getValor()); //tarifa preferencial   
        pasada.setTipoCobro(DataTipoCobro.prePago);
        Vehiculo vehiculo = existeVehiculo(tag, matricula);
        pasada.setVehiculo( vehiculo);

        if(vehiculo == null){
            repo.addVehiculo(vehiculo);
        }

        boolean habilitado = gestionCliente.realizarPrePago(tarifaPreferencial.getValor(), tag);
        if (!habilitado) {
            //fallo el cobro prepago, intento con la tarjeta (postPago)
            pasada.setTipoCobro(DataTipoCobro.postPago);
            habilitado = gestionCliente.realizarPostPago(tarifaPreferencial.getValor(), tag);
        }

        if (!habilitado) {
            habilitado = suciveService.notificarPago(matricula, tarifaComun.getValor());
            pasada.setTipoCobro(DataTipoCobro.sucive);
            pasada.setCosto(tarifaComun.getValor());
            evento.publicarPagoSucive(tag);
        }

        if (habilitado) {
            repo.addPasada(pasada);
            gestionClienteFacade.registrarPasadaPorPeaje(pasada);
            evento.publicarVehiculoNacional(tag);
        }
        return habilitado;
    }

    private Vehiculo existeVehiculo(String tag, String matricula) {
        Vehiculo vehiculo = repo.findByTag(tag);
        if (vehiculo == null) {
            vehiculo = repo.findByMatricula(matricula);
            if (vehiculo == null) {
                //error grave el vehiculo no esta en el sistema
                evento.publicarVehiculoNoEncontrado(
                        "Vehiculo no encontrado: " + tag + " " + matricula);
            }
        }
        return vehiculo;
    }

    @Override
    public void actualizarTarifaComun(double importe) {
        tarifaComun.setValor(importe);
        repo.setTarifaComun(importe);
    }

    @Override
    public void actualizarTarifaPreferencial(double importe) {
        tarifaPreferencial.setValor(importe);
        repo.setTarifaPreferencial(importe);
    }

    
}
