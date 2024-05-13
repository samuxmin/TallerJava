
package org.tallerjava.moduloMediosDePago.aplicacion;

import org.tallerjava.moduloMediosDePago.dominio.Usuario;
import org.tallerjava.moduloMediosDePago.dominio.Tarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;

import org.tallerjava.moduloMediosDePago.dominio.RangoFechas;

import org.tallerjava.moduloMediosDePago.dominio.Pagos;
import org.tallerjava.moduloMediosDePago.dominio.Vinculo;
import org.tallerjava.moduloMediosDePago.dominio.PasadaPorPeaje;
import org.tallerjava.moduloMediosDePago.dominio.ClienteTelepeaje;
import org.tallerjava.moduloMediosDePago.dominio.CuentaPOSTPaga;
import org.tallerjava.moduloMediosDePago.dominio.CuentaPREPaga;
import org.tallerjava.moduloMediosDePago.dominio.TarifaPreferencial;
import org.tallerjava.moduloMediosDePago.dominio.DataTipoCobro;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

//import jakarta.annotation.PostConstruct;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MetodoPagoServiceImpl implements MetodoPagoService {

    
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void altaCliente(Usuario usuario, Tarjeta tarjeta) {

        if (usuario == null || tarjeta == null) {
            throw new IllegalArgumentException("Usuario y Tarjeta no pueden ser nulos");
        }

        TarifaPreferencial tarifapref = new TarifaPreferencial(1000); //lo actualizo con la funcion del bruno 

        int nroCuentaIncr = usuarios.size()+ 1;

        CuentaPOSTPaga cuentaPostPaga = new CuentaPOSTPaga(LocalDate.now(),nroCuentaIncr,tarjeta); //es un autogenerado el 1 en la bd
        CuentaPREPaga cuentaPrePaga = null; //no sirve si tiene tarjeta no es prepaga

        ClienteTelepeaje cliente = new ClienteTelepeaje(tarifapref, cuentaPostPaga, cuentaPrePaga);

        usuario.setClienteTelepeaje(cliente);

        usuarios.add(usuario);

    }


    @Override
    public void notificarPago(Usuario usuario, Vehiculo vehiculo, double importe, Tarjeta tarjeta){
        if (usuario == null || vehiculo == null || tarjeta == null) {
            throw new IllegalArgumentException("Usuario, Vehiculo y Tarjeta no pueden ser nulos");
        }

        if (importe <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor a 0");
        }

        PasadaPorPeaje pasada = new PasadaPorPeaje(importe, LocalDate.now(), DataTipoCobro.postPago, vehiculo);

        ClienteTelepeaje clienteTelepeaje = usuario.getClienteTelepeaje();
        CuentaPOSTPaga cuentaPostPaga = clienteTelepeaje.getCuentaPostPaga();

    
        if (!tarjeta.equals(cuentaPostPaga.getTarjeta())) {
            throw new IllegalArgumentException("La tarjeta no es la misma que la asociada a la cuenta");
        }

        //TODO cuentaPostPaga.agregarPago(pasada); hay que arreglarlo porque tarjeta no tiene saldo.  PREGUNTAR AL PROFE 

        List<PasadaPorPeaje> pasadasPorPeaje = vehiculo.getPasadasPorPeaje();
        pasadasPorPeaje.add(pasada);
    }

    @Override
    public List<Pagos> consultaDePagosPorDia(RangoFechas rangoFechas) {
        List<Pagos> pagos = new ArrayList<>();
        for(Usuario usuario : usuarios){
            List<Vinculo> vinculos = usuario.getVinculosVehiculos();
            for(Vinculo vinculo : vinculos){
                Vehiculo vehiculo = vinculo.getVehiculo();
                List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
                for(PasadaPorPeaje pasada : pasadasVehiculo){
                    if(pasada.getFecha().isAfter(rangoFechas.getFechaInicial()) && pasada.getFecha().isBefore(rangoFechas.getFechaFinal())){
                        Pagos pago = new Pagos(pasada.getCosto(), pasada.getFecha(), usuario, vehiculo);
                        pagos.add(pago);
                    }
                }
            }
        }
        return pagos;
    }

    @Override
    public List<Pagos> consultaDePagosPorCliente(Usuario usuario) {
        List<Pagos> pagos = new ArrayList<>();
        for(Vinculo vinculo : usuario.getVinculosVehiculos()){
            Vehiculo vehiculo = vinculo.getVehiculo();
            List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
            for(PasadaPorPeaje pasada : pasadasVehiculo){
                Pagos pago = new Pagos(pasada.getCosto(), pasada.getFecha(), usuario, vehiculo);
                pagos.add(pago);
            }
        }
        return pagos;
    }
    
    @Override
    public List<Pagos> consultaDePagosClienteVehiculo(Usuario usuario, Vehiculo vehiculo) {
        List<Pagos> pagos = new ArrayList<>();
        for(Vinculo vinculo : usuario.getVinculosVehiculos()){
            if(vinculo.getVehiculo().equals(vehiculo)){
                List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
                for(PasadaPorPeaje pasada : pasadasVehiculo){
                    Pagos pago = new Pagos(pasada.getCosto(), pasada.getFecha(), usuario, vehiculo);
                    pagos.add(pago);
                }
            }
        }
        return pagos;
    }

}
