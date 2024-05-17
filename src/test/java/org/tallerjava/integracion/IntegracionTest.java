package org.tallerjava.integracion;

import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteService;
import org.tallerjava.moduloGestionCliente.dominio.clases.*;
import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteServiceImpl;
import org.tallerjava.moduloGestionCliente.infraestructura.persistencia.UsuariosRepoImpl;
import org.tallerjava.moduloMediosDePago.aplicacion.MetodoPagoServiceImpl;
import org.tallerjava.moduloMediosDePago.intraestructura.persistencia.PagoRepositorioImpl;
import org.tallerjava.moduloMonitoreo.aplicacion.MonitoreoServiceImpl;
import org.tallerjava.moduloMonitoreo.interfase.MonitoreoService;
import org.tallerjava.moduloPeaje.aplicacion.PeajeService;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeajeImpl;
import org.tallerjava.moduloPeaje.infraestructura.persistencia.PeajeRepositorioImpl;
import org.tallerjava.moduloPeaje.interfase.evento.out.PublicadorEventoImpl;
import org.tallerjava.moduloSucive.aplicacion.SuciveServiceImpl;
import org.tallerjava.moduloSucive.infraestructura.persistencia.SuciveRepositorioImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@EnableAutoWeld

@AddPackages(SuciveRepositorioImpl.class)
@AddPackages(PeajeRepositorioImpl.class)
@AddPackages(GestionClienteServiceImpl.class)
@AddPackages(SuciveServiceImpl.class)
@AddPackages(ServicioPeajeImpl.class)
@AddPackages(PublicadorEventoImpl.class)
@AddPackages(MetodoPagoServiceImpl.class)
@AddPackages(PagoRepositorioImpl.class)
@AddPackages(MonitoreoServiceImpl.class)
@AddPackages(UsuariosRepoImpl.class)
public class IntegracionTest {
    @Inject
    GestionClienteService moduloGestionCliente;

    @Inject
    PeajeService moduloPeaje;

    @Inject
    MonitoreoService moduloMonitoreo;

    @Test
    public void pasajeVehiculoExtranjeroPREPagoTest() {
        Usuario usuario = new Usuario();
        usuario.setCi("ci123");
        moduloGestionCliente.altaClienteTelepeaje(usuario);
       // cliente.cargarSaldo(usuario,200000);
        VehiculoExtranjero vehiculo = new VehiculoExtranjero();
        vehiculo.setTag(new Tag("tag123"));
        moduloGestionCliente.vincularVehiculo(vehiculo,usuario);
        assertFalse(moduloPeaje.estaHabilitadoSincronico("tag123",""));
        moduloGestionCliente.cargarSaldo(usuario,200000);
        assertTrue(moduloPeaje.estaHabilitadoSincronico("tag123",""));
    }
    @Test
    public void pasajeVehiculoExtranjeroPOSTPagoTest() {
        Usuario usuario = new Usuario();
        usuario.setCi("12345");
        moduloGestionCliente.altaClienteTelepeaje(usuario);
        // cliente.cargarSaldo(usuario,200000);
        VehiculoExtranjero vehiculo = new VehiculoExtranjero();
        vehiculo.setTag(new Tag("123"));
        moduloGestionCliente.vincularVehiculo(vehiculo,usuario);
        assertFalse(moduloPeaje.estaHabilitadoSincronico("123",""));
        moduloGestionCliente.asociarTarjeta(usuario, new Tarjeta(LocalDate.parse("2026-02-21"),"",123));
        assertTrue(moduloPeaje.estaHabilitadoSincronico("123",""));
    }
    @Test
    public void pasajeVehiculoNacionalPREPagoTest() {
        Usuario usuario = new Usuario();
        usuario.setCi("123");
        moduloGestionCliente.altaClienteTelepeaje(usuario);
        // cliente.cargarSaldo(usuario,200000);
        VehiculoNacional vehiculo = new VehiculoNacional();
        vehiculo.setMatricula(new Matricula("123matr"));
        vehiculo.setTag(new Tag("123"));
        moduloGestionCliente.vincularVehiculo(vehiculo,usuario);
       // assertFalse(peaje.estaHabilitadoSincronico("123",""));
        moduloGestionCliente.cargarSaldo(usuario,200000);
        assertTrue(moduloPeaje.estaHabilitadoSincronico("123","123matr"));
    }
    @Test
    public void pasajeVehiculoNacionalPOSTPagoTest() {
        Usuario usuario = new Usuario();
        usuario.setCi("12345");
        moduloGestionCliente.altaClienteTelepeaje(usuario);
        // cliente.cargarSaldo(usuario,200000);
        VehiculoNacional vehiculo = new VehiculoNacional();
        vehiculo.setTag(new Tag("123"));
        vehiculo.setMatricula(new Matricula("123matr"));
        moduloGestionCliente.vincularVehiculo(vehiculo,usuario);
        //assertFalse(peaje.estaHabilitadoSincronico("123",""));
        moduloGestionCliente.asociarTarjeta(usuario, new Tarjeta(LocalDate.parse("2026-02-21"),"",123));
        assertTrue(moduloPeaje.estaHabilitadoSincronico("123",""));
    }
    @Test
    public void pasajeVehiculoSuciveTest(){
        Usuario usuario = new Usuario();
        usuario.setCi("12345");
        moduloGestionCliente.altaClienteTelepeaje(usuario);
        VehiculoNacional vehiculo = new VehiculoNacional();
        vehiculo.setTag(new Tag("123"));
        vehiculo.setMatricula(new Matricula("123matr"));
        moduloGestionCliente.vincularVehiculo(vehiculo,usuario);
        assertTrue(moduloPeaje.estaHabilitadoSincronico("123",""));
    }
}
