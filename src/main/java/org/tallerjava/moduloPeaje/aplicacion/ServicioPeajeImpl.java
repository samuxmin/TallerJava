package org.tallerjava.moduloPeaje.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteService;
import org.tallerjava.moduloPeaje.dominio.TarifaComun;
import org.tallerjava.moduloPeaje.dominio.TarifaPreferencial;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.dominio.VehiculoNacional;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;
import org.tallerjava.moduloPeaje.interfase.evento.out.PublicadorEvento;
import org.tallerjava.moduloSucive.aplicacion.SuciveService;

@ApplicationScoped
public class ServicioPeajeImpl implements PeajeService {
    @Inject
    private PeajeRepositorio repo;

    @Inject
    private PublicadorEvento evento;

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
                mandarAQueueDePagos(vehiculo);
                habilitado = true;
            } else {
                habilitado = procesarVehiculoExtranjero(tag, vehiculo);
            }
        }
        return habilitado;
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

            //según las reglas del negocio, lo primero es cobrar con PrePago

            habilitado = gestionCliente.realizarPrePago(tarifa.getValor(),tag);
            if (!habilitado) {
                //fallo el cobro prepago, intento con la tarjeta (postPago)
                habilitado = gestionCliente.realizarPostPago( tarifa.getValor(),tag);
            }

        if (!habilitado) {
            if(vehiculo instanceof VehiculoNacional) {
                VehiculoNacional vehiculoNacional =(VehiculoNacional) vehiculo;
                evento.publicarPasajeVehiculo("Paso vehiculo nacional tag: " + tag + " matricula: " + vehiculoNacional.getMatricula().getNroMatricula());
                habilitado = suciveService.notificarPago(vehiculoNacional.getMatricula().getNroMatricula(), repo.obtenerTarifaComun().getValor());
            }else{
                evento.publicarPasajeVehiculo("Paso vehiculo con tag: " + tag);

            }
        }
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
