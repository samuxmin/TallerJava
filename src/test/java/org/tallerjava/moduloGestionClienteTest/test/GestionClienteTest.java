
package org.tallerjava.moduloGestionClienteTest.test;

import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.tallerjava.moduloGestionCliente.interfase.local.GestionClienteServiceImpl;
import org.tallerjava.moduloGestionCliente.aplicacion.GestionClienteService;
import org.tallerjava.moduloGestionCliente.dominio.clases.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@EnableAutoWeld

@AddPackages(GestionClienteServiceImpl.class)
public class GestionClienteTest {
    @Inject
    GestionClienteService gestionClienteService ;//= new GestionClienteServiceImpl();

    //Esta funcion prueba el vincular, desvincular y mostrar vehiculos del usuario
    @Test
    public void usuarioVehiculosTest() {
        Usuario usuario = new Usuario("mailUsr","nombreUsr","1231234",new ArrayList<Vinculo>(),new ClienteTelepeaje());
        //public VehiculoNacional(List<PasadaPorPeaje> pasadasPorPeaje, Vinculo v, Matricula matricula, Tag tag) {
        Vehiculo vehiculo = new VehiculoNacional(new ArrayList<PasadaPorPeaje>(),null,new Matricula("123"), new Tag("123"));
        gestionClienteService.vincularVehiculo(vehiculo,usuario);
        assertTrue(gestionClienteService.mostraVehiculosVinculados(usuario).contains(vehiculo));
        gestionClienteService.desvincularVehiculo(usuario,vehiculo);
        assertFalse(gestionClienteService.mostraVehiculosVinculados(usuario).contains(vehiculo));
    }

    @Test
    public void saldoTest() {
        //public void cargarSaldo(Usuario usuario,float importe);
        //public float consultarSaldo(Usuario usuario);
        Usuario usuario = new Usuario("mailUsr","nombreUsr","1231234",new ArrayList<Vinculo>(),new ClienteTelepeaje());
        Vehiculo vehiculo = new VehiculoNacional(new ArrayList<PasadaPorPeaje>(),null,new Matricula("123"), new Tag("123"));
        gestionClienteService.vincularVehiculo(vehiculo,usuario);
        gestionClienteService.altaClienteTelepeaje(usuario);

        int carga1 = 100;
        int carga2 = 200;

        gestionClienteService.cargarSaldo(usuario,carga1);

        gestionClienteService.cargarSaldo(usuario,carga2);
        System.out.println(gestionClienteService.consultarSaldo(usuario));
        assertEquals(carga1 + carga2,gestionClienteService.consultarSaldo(usuario));
        gestionClienteService.realizarPrePago(carga1,"123");
        assertEquals(carga2 ,gestionClienteService.consultarSaldo(usuario));
    }
    @Test
    public void asociarTarjetaTest() {
        Usuario usuario = new Usuario("mailUsr", "nombreUsr", "1231234", new ArrayList<Vinculo>(), new ClienteTelepeaje());
        Tarjeta tarjeta = new Tarjeta(LocalDate.parse("2026-02-02"), "nombreUsr??", 123123);
        gestionClienteService.altaClienteTelepeaje(usuario);
        gestionClienteService.asociarTarjeta(usuario, tarjeta);
        assertEquals(tarjeta, usuario.getClienteTelepeaje().getCuentaPostPaga().getTarjeta());
    }
}

