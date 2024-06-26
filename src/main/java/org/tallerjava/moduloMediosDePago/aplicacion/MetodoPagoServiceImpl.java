package org.tallerjava.moduloMediosDePago.aplicacion;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.tallerjava.moduloGestionCliente.interfase.evento.out.AsociarTarjeta;
import org.tallerjava.moduloMediosDePago.dominio.ClienteTelepeaje;
import org.tallerjava.moduloMediosDePago.dominio.CuentaPOSTPaga;
import org.tallerjava.moduloMediosDePago.dominio.Pago;
import org.tallerjava.moduloMediosDePago.dominio.RangoFechas;
import org.tallerjava.moduloMediosDePago.dominio.Tarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Usuario;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;
import org.tallerjava.moduloMediosDePago.dominio.repositorio.PagoRepositorio;
import org.tallerjava.moduloMediosDePago.interfase.evento.out.CobroRechazado;
import org.tallerjava.moduloMediosDePago.interfase.evento.out.CobroTarjeta;


import jakarta.ws.rs.core.Response;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class MetodoPagoServiceImpl implements MetodoPagoService {
    @Inject
    private PagoRepositorio pagoRepositorio;

    @Inject
    Event<CobroTarjeta> cobroTarjetaEvent;
    @Inject
    Event<CobroRechazado> cobroRechazadoEvent;

    public void setPagoRepositorio(PagoRepositorio pagoRepositorio) {
        this.pagoRepositorio = pagoRepositorio;
    }

    @Override
    public boolean altaCliente(@Observes AsociarTarjeta asociarTarjeta) {
        if (asociarTarjeta == null || asociarTarjeta.getUsuario() == null || asociarTarjeta.getTarjeta() == null) {
            System.err.println("Error: Datos incompletos para asociar tarjeta.");
            return false;
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(asociarTarjeta.getUsuario().getEmail());
        usuario.setCi(asociarTarjeta.getUsuario().getCi());
        usuario.setNombre(asociarTarjeta.getUsuario().getNombre());

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setFechaVto(asociarTarjeta.getTarjeta().getFechaVto());
        tarjeta.setNombre(asociarTarjeta.getTarjeta().getNombre());
        tarjeta.setNro(asociarTarjeta.getTarjeta().getNro());
        tarjeta.setUsuarioAsociado(usuario);

        if (pagoRepositorio.existeUsuario(usuario.getCi())) {
            System.err.println("Error: El usuario ya existe.");
            return false;
        }

        ClienteTelepeaje clienteTelepeaje = new ClienteTelepeaje();
        CuentaPOSTPaga cuentaPostPaga = new CuentaPOSTPaga();
        cuentaPostPaga.setTarjeta(tarjeta);
        clienteTelepeaje.setCuentaPostPaga(cuentaPostPaga);
        usuario.setClienteTelepeaje(clienteTelepeaje);

        try {
            pagoRepositorio.registrarUsuario(usuario);
        } catch (Exception e) {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
            return false;
        }

        return true;
    }


    @Override
    public boolean notificarPago(String ci, String tag, double importe, long nroTarjeta) {
        Usuario usuario;
        try {
            usuario = pagoRepositorio.getUsuario(ci);
            if (usuario == null || usuario.getClienteTelepeaje() == null) {
                cobroRechazadoEvent.fire(new CobroRechazado(ci, nroTarjeta, importe, "Cuenta Postpaga no registrada para usuario"));
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el usuario: " + e.getMessage());
            cobroRechazadoEvent.fire(new CobroRechazado(ci, nroTarjeta, importe, "Error al obtener el usuario"));
            return false;
        }

        CuentaPOSTPaga cuentaPostPaga = usuario.getClienteTelepeaje().getCuentaPostPaga();
        if (cuentaPostPaga == null) {
            cobroRechazadoEvent.fire(new CobroRechazado(ci, nroTarjeta, importe, "Cuenta Postpaga no registrada para usuario"));
            return false;
        }

        Tarjeta tarjeta = cuentaPostPaga.getTarjeta();
        if (tarjeta == null || !tarjeta.esValida() || tarjeta.getNro() != nroTarjeta) {
            cobroRechazadoEvent.fire(new CobroRechazado(ci, nroTarjeta, importe, "Tarjeta invalida"));
            return false;
        }

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTag(tag);


        Response response = respuestaMockPagos(nroTarjeta);
        if(response.getStatus() != 200) {
            cobroRechazadoEvent.fire(new CobroRechazado(ci, nroTarjeta, importe, "Error al notificar el pago"));
            return false;
        }


        Pago pago = new Pago(usuario, vehiculo, importe, tarjeta, LocalDate.now());
        try {
            pagoRepositorio.registrarPago(pago);
        } catch (Exception e) {
            System.err.println("Error al registrar el pago: " + e.getMessage());
            cobroRechazadoEvent.fire(new CobroRechazado(ci, nroTarjeta, importe, "Error al registrar el pago"));
            return false;
        }

        CobroTarjeta cobroTarjeta = new CobroTarjeta(ci, nroTarjeta, importe);
        cobroTarjetaEvent.fire(cobroTarjeta);
        return true;
    }


    public Response respuestaMockPagos(long nroTarjeta) {
        Client httpClient = ClientBuilder.newClient();
    
        Response response = httpClient
                .target("http://localhost:8080/TallerJavaMediosPagoMock/api/mockMediosDePago/notificarPagoMock/" + nroTarjeta)
                .request(MediaType.APPLICATION_JSON)
                .get();
    
        return response;

    }
    
    @Override
    public List<Pago> consultaDePagosPorDia(RangoFechas rangoFechas) {
        List<Pago> pagosPorRangoFechas = pagoRepositorio.obtenerPagosPorRangoFechas(rangoFechas);
        return agruparPagosPorDia(pagosPorRangoFechas);
    }

    @Override
    public List<Pago> consultaDePagosPorCliente(Usuario usuario) {
        List<Pago> pagosPorUsuario = pagoRepositorio.obtenerPagosPorUsuario(usuario);
        return pagosPorUsuario;
    }

    @Override
    public List<Pago> consultaDePagosClienteVehiculo(Usuario usuario, Vehiculo vehiculo) {
        List<Pago> pagosPorUsuarioYVehiculo = pagoRepositorio.obtenerPagosPorUsuarioYVehiculo(usuario, vehiculo);
        return pagosPorUsuarioYVehiculo;
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

/*

 */