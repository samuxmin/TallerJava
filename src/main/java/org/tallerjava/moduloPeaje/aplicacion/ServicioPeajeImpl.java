package org.tallerjava.moduloPeaje.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteService;
import org.tallerjava.moduloGestionCliente.dominio.eventos.VinculoEvent;
import org.tallerjava.moduloPeaje.dominio.*;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;
import org.tallerjava.moduloPeaje.interfase.evento.out.PublicadorEvento;
import org.tallerjava.moduloSucive.aplicacion.SuciveService;

@ApplicationScoped
public class ServicioPeajeImpl implements PeajeService {
    @Inject
    private PeajeRepositorio repo;

    @Inject
    private PublicadorEvento evento;// = new PublicadorEvento();

    @Inject
    GestionClienteService gestionCliente;

    @Inject
    SuciveService suciveService;
    //private ServicioPagoFacade servicioPagoFacade;

    @Override
    public boolean estaHabilitadoSincronico(String tag, String matricula) {
        boolean habilitado = false;
        Vehiculo vehiculo = existeVehiculo(tag, matricula);
        if (vehiculo != null) {
            if (vehiculo instanceof VehiculoNacional) {
                //mandarAQueueDePagos(vehiculo);
                habilitado = procesarVehiculoNacional(tag,vehiculo);
            } else {
                habilitado = procesarVehiculoExtranjero(tag, vehiculo);
            }
        }
        return habilitado;
    }
    private void addVehiculo(@Observes VinculoEvent vinculoEvent){
        Vehiculo vehiculo = null;
        if(vinculoEvent.getTipo() == "extranjero"){
            VehiculoExtranjero vehiculoE = new VehiculoExtranjero();
            vehiculoE.setTag(new Tag(vinculoEvent.getTag()));
            vehiculo = vehiculoE;
        }else{
            VehiculoNacional vehiculoE = new VehiculoNacional();
            vehiculoE.setTag(new Tag(vinculoEvent.getTag()));
            vehiculoE.setMatricula(new Matricula(vinculoEvent.getMatricula()));

            vehiculo = vehiculoE;
        }
            repo.addVehiculo(vehiculo);
    }
    private boolean  procesarVehiculoExtranjero(String tag, Vehiculo vehiculo) {
        evento.publicarPasajeVehiculo("Paso vehiculo con tag " + tag);
        boolean habilitado = false;
        //todos los vehiculos extranjeros son preferenciales
        TarifaPreferencial tarifa = repo.obtenerTarifaPreferencial();
        //según las reglas del negocio, lo primero es cobrar con PrePago
        habilitado = gestionCliente.realizarPrePago( tarifa.getValor(),tag );
        if (!habilitado) {
            //fallo el cobro prepago, intento con la tarjeta (postPago)
            habilitado = gestionCliente.realizarPostPago( tarifa.getValor(),tag);
            if (!habilitado) {
                //TODO habilitado = servicioMonitoreoFacade.notificarCobroTarjetaRechazado();
                //TODO mando evento al modulo de monitoreo el auto no pasa
            }
        }
        return habilitado;
    }


    private boolean  procesarVehiculoNacional(String tag, Vehiculo vehiculo) {
        boolean habilitado = false;
        TarifaPreferencial tarifa = repo.obtenerTarifaPreferencial();
            habilitado = gestionCliente.realizarPrePago(tarifa.getValor(),tag);
            if (!habilitado) {
                //fallo el cobro prepago, intento con la tarjeta (postPago)
                habilitado = gestionCliente.realizarPostPago( tarifa.getValor(),tag);
            }

        if (!habilitado) {
                VehiculoNacional vehiculoNacional =(VehiculoNacional) vehiculo;
                evento.publicarPasajeVehiculo("Paso vehiculo nacional tag: " + tag + " matricula: " + vehiculoNacional.getMatricula().getNroMatricula());
                habilitado = suciveService.notificarPago(vehiculoNacional.getMatricula().getNroMatricula(), repo.obtenerTarifaComun().getValor());
            }
        System.out.println(tag);
        return habilitado;
    }

    private void mandarAQueueDePagos(Vehiculo vehiculo) {
        //TODO esto lo vamos a hacer más adelante.
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
        TarifaComun tarifa = repo.obtenerTarifaComun();
        tarifa.setValor(importe);
    }

    @Override
    public void actualizarTarifaPreferencial(double importe) {
        TarifaPreferencial tarifa = repo.obtenerTarifaPreferencial();
        tarifa.setValor(importe);
    }
}
