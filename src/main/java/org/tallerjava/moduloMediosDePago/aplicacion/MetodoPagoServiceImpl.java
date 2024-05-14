package org.tallerjava.moduloMediosDePago.aplicacion;

import jakarta.enterprise.event.Observes;
import org.tallerjava.moduloGestionCliente.dominio.eventos.AsociarTarjeta;
import org.tallerjava.moduloMediosDePago.dominio.*;
import org.tallerjava.moduloMediosDePago.dominio.repositorio.PagoRepositorio;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;

@ApplicationScoped
public class MetodoPagoServiceImpl implements MetodoPagoService {
    
    @Inject
    private PagoRepositorio pagoRepositorio;

    
    public void setPagoRepositorio(PagoRepositorio pagoRepositorio) {
        this.pagoRepositorio = pagoRepositorio;
    }
    

    @Override
    public boolean altaCliente(@Observes AsociarTarjeta asociarTarjeta) {

        Usuario usuario = new Usuario();
        usuario.setEmail(asociarTarjeta.getUsuario().getEmail());
        usuario.setCi(asociarTarjeta.getUsuario().getCi());

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setFechaVto(asociarTarjeta.getTarjeta().getFechaVto());
        tarjeta.setNombre(asociarTarjeta.getTarjeta().getNombre());
        tarjeta.setNro(asociarTarjeta.getTarjeta().getNro());

        ClienteTelepeaje clienteTelepeaje = new ClienteTelepeaje();
        CuentaPOSTPaga cuentaPostPaga = new CuentaPOSTPaga();
        cuentaPostPaga.setTarjeta(tarjeta);

        clienteTelepeaje.setCuentaPostPaga(cuentaPostPaga);
        usuario.setClienteTelepeaje(clienteTelepeaje);

        pagoRepositorio.registrarUsuario(usuario);
        if (pagoRepositorio.existeUsuario(usuario.getCi())) {
            return false;
        }


        clienteTelepeaje.setCuentaPostPaga(cuentaPostPaga);
        usuario.setClienteTelepeaje(clienteTelepeaje);

        pagoRepositorio.registrarUsuario(usuario);

        return true;
    }


    @Override
    public boolean notificarPago(String ci, String tag, double importe, int nroTarjeta) {
        Usuario usuario = pagoRepositorio.getUsuario(ci);
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTag(tag);
        Tarjeta tarjeta = usuario.getClienteTelepeaje().getCuentaPostPaga().getTarjeta();

        ClienteTelepeaje clienteTelepeaje = usuario.getClienteTelepeaje();
        if (clienteTelepeaje.getCuentaPostPaga().getTarjeta().esValida()) {
            if(tarjeta.getNro() == nroTarjeta){
                return true;
            }
            Pago pago = new Pago(usuario, vehiculo, importe, tarjeta, LocalDate.now());
            pagoRepositorio.registrarPago(pago);


            return true;
        }

        return false;
    }
/* 
    private void procesarPagoConTarjeta(Tarjeta tarjeta, double importe) {
        //pago con tarjeta
    }
*/
    @Override
    public List<Pago> consultaDePagosPorDia(RangoFechas rangoFechas) {
        List<Pago> pagosPorRangoFechas = pagoRepositorio.obtenerPagosPorRangoFechas(rangoFechas);
        return agruparPagosPorDia(pagosPorRangoFechas);
    }

    @Override
    public List<Pago> consultaDePagosPorCliente(Usuario usuario) {
        List<Pago> pagosPorUsuario = pagoRepositorio.obtenerPagosPorUsuario(usuario);
        return agruparPagosPorDia(pagosPorUsuario);
    }

    @Override
    public List<Pago> consultaDePagosClienteVehiculo(Usuario usuario, Vehiculo vehiculo) {
        List<Pago> pagosPorUsuarioYVehiculo = pagoRepositorio.obtenerPagosPorUsuarioYVehiculo(usuario, vehiculo);
        return agruparPagosPorDia(pagosPorUsuarioYVehiculo);
    }


    public List<Pago> agruparPagosPorDia(List<Pago> pagos) {
    List<Pago> pagosPorDia = new ArrayList<>();

        for (Pago pago : pagos) {
            boolean fechaEncontrada = false;
            for (Pago pagoDia : pagosPorDia) {
                if (pagoDia.getFecha().equals(pago.getFecha())) {
                    pagoDia.setImporte(pagoDia.getImporte() + pago.getImporte());
                    fechaEncontrada = true;
                    break;
                }
            }
            if (!fechaEncontrada) {
                pagosPorDia.add(new Pago(pago.getUsuario(), pago.getVehiculo(), pago.getImporte(), pago.getTarjeta(), pago.getFecha()));
            }
        }

        return pagosPorDia;
    }

}