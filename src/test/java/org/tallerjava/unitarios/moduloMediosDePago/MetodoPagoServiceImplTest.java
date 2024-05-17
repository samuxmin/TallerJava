package org.tallerjava.unitarios.moduloMediosDePago;

import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.tallerjava.moduloGestionCliente.infraestructura.persistencia.UsuariosRepoImpl;
import org.tallerjava.moduloMediosDePago.aplicacion.MetodoPagoServiceImpl;
import org.tallerjava.moduloMediosDePago.dominio.ClienteTelepeaje;
import org.tallerjava.moduloMediosDePago.dominio.CuentaPOSTPaga;
import org.tallerjava.moduloMediosDePago.dominio.Pago;
import org.tallerjava.moduloMediosDePago.dominio.RangoFechas;
import org.tallerjava.moduloMediosDePago.dominio.Tarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Usuario;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;
import org.tallerjava.moduloMediosDePago.dominio.repositorio.PagoRepositorio;
import org.tallerjava.moduloMediosDePago.intraestructura.persistencia.PagoRepositorioImpl;

import jakarta.inject.Inject;

import org.tallerjava.moduloGestionCliente.dominio.eventos.AsociarTarjeta;
import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EnableAutoWeld

@AddPackages(GestionClienteServiceImpl.class)
@AddPackages(MetodoPagoServiceImpl.class)
@AddPackages(PagoRepositorioImpl.class)
@AddPackages(UsuariosRepoImpl.class)
public class MetodoPagoServiceImplTest {

    private PagoRepositorio pagoRepositorio;

    @Inject
    private MetodoPagoServiceImpl metodoPagoService;

    private Usuario usuario;
    private Vehiculo vehiculo;
    private RangoFechas rangoFechas;

    @BeforeEach
    public void setup() {
        pagoRepositorio = Mockito.mock(PagoRepositorio.class);
        //metodoPagoService = new MetodoPagoServiceImpl();

        metodoPagoService.setPagoRepositorio(pagoRepositorio);
        usuario = Mockito.mock(Usuario.class);
        vehiculo = new Vehiculo();
        rangoFechas = new RangoFechas();
    }

    @Test
    public void testAltaCliente() {

        AsociarTarjeta asociarTarjeta = mock(AsociarTarjeta.class);
        org.tallerjava.moduloGestionCliente.dominio.clases.Usuario moduloGestionUsuario = mock(org.tallerjava.moduloGestionCliente.dominio.clases.Usuario.class);
        org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta moduloGestionTarjeta = mock(org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta.class);


        when(asociarTarjeta.getUsuario()).thenReturn(moduloGestionUsuario);
        when(asociarTarjeta.getTarjeta()).thenReturn(moduloGestionTarjeta);
        when(moduloGestionUsuario.getCi()).thenReturn("123456");


        when(pagoRepositorio.existeUsuario("123456")).thenReturn(false);
        assertTrue(metodoPagoService.altaCliente(asociarTarjeta));


        when(pagoRepositorio.existeUsuario("123456")).thenReturn(true);
        assertFalse(metodoPagoService.altaCliente(asociarTarjeta));
    }

    @Test
    public void testNotificarPago() {

        ClienteTelepeaje clienteTelepeaje = mock(ClienteTelepeaje.class);
        CuentaPOSTPaga cuentaPostPaga = mock(CuentaPOSTPaga.class);
        Tarjeta tarjetaPostPaga = mock(Tarjeta.class);


        when(usuario.getClienteTelepeaje()).thenReturn(clienteTelepeaje);
        when(clienteTelepeaje.getCuentaPostPaga()).thenReturn(cuentaPostPaga);
        when(cuentaPostPaga.getTarjeta()).thenReturn(tarjetaPostPaga);
        when(tarjetaPostPaga.esValida()).thenReturn(true);
        when(pagoRepositorio.getUsuario("123")).thenReturn(usuario);


        when(tarjetaPostPaga.getNro()).thenReturn(123456);
        assertTrue(metodoPagoService.notificarPago("123", "TAG123", 100.0, 123456));


        when(tarjetaPostPaga.getNro()).thenReturn(654321);
        assertFalse(metodoPagoService.notificarPago("123", "TAG123", 100.0, 123456));

    }

    @Test
    public void testConsultaDePagosPorDia() {
        List<Pago> pagos = new ArrayList<>();
        when(pagoRepositorio.obtenerPagosPorRangoFechas(rangoFechas)).thenReturn(pagos);
        assertEquals(pagos, metodoPagoService.consultaDePagosPorDia(rangoFechas));
    }

    @Test
    public void testConsultaDePagosPorCliente() {
        List<Pago> pagos = new ArrayList<>();
        when(pagoRepositorio.obtenerPagosPorUsuario(usuario)).thenReturn(pagos);
        assertEquals(pagos, metodoPagoService.consultaDePagosPorCliente(usuario));
    }

    @Test
    public void testConsultaDePagosClienteVehiculo() {
        List<Pago> pagos = new ArrayList<>();
        when(pagoRepositorio.obtenerPagosPorUsuarioYVehiculo(usuario, vehiculo)).thenReturn(pagos);
        assertEquals(pagos, metodoPagoService.consultaDePagosClienteVehiculo(usuario, vehiculo));
    }

    @Test
    public void testAgruparPagosPorDia() {
        List<Pago> pagos = new ArrayList<>();
        Pago pago1 = new Pago(usuario, vehiculo, 100.0, new Tarjeta(), LocalDate.now());
        Pago pago2 = new Pago(usuario, vehiculo, 200.0, new Tarjeta(), LocalDate.now());
        pagos.add(pago1);
        pagos.add(pago2);
        List<Pago> pagosPorDia = metodoPagoService.agruparPagosPorDia(pagos);
        assertEquals(1, pagosPorDia.size());
        assertEquals(300.0, pagosPorDia.get(0).getImporte());
    }
}