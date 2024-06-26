package org.tallerjava.moduloGestionCliente.startup;

import org.tallerjava.moduloGestionCliente.dominio.clases.Nacionalidad;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloGestionCliente.dominio.datatypes.DataVehiculo;
import org.tallerjava.moduloGestionCliente.interfase.api.GestionClienteREST;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class StartupBean {

    @Inject
    private GestionClienteREST gestionClienteService;
    

    @PostConstruct
    public void init() {
        // Insertar datos de prueba
        insertarUsuarios();
        insertarVehiculos();
       // insertarTarjetas();
        // Otros datos de prueba...
    }

    private void insertarUsuarios() {
        Usuario usuario1 = new Usuario();
        usuario1.setCi("CI1");
        usuario1.setNombre("Juan");
        usuario1.setEmail("juan@mail.com");
        usuario1.setPassword("pass123");

        Usuario usuario2 = new Usuario();
        usuario2.setCi("CI2");
        usuario2.setNombre("Extranjero");
        usuario2.setEmail("ex@mail.com");
        usuario2.setPassword("pass123");    

        gestionClienteService.altaUsuario(usuario1);
        gestionClienteService.altaUsuario(usuario2);
    }

    private void insertarVehiculos() {
        //Vehiculo vehiculo1 = new Vehiculo("123ABC", "Toyota", "Corolla", 2010);
        Vehiculo vehiculo1 = new Vehiculo();
        vehiculo1.setMatricula("mat1");
        vehiculo1.setTag("tag1");
        vehiculo1.setNacionalidad(Nacionalidad.NACIONAL);

        Vehiculo vehiculo2 = new Vehiculo();
        vehiculo2.setMatricula("mat2");
        vehiculo2.setTag("tag2");
        vehiculo2.setNacionalidad(Nacionalidad.EXTRANJERO);

        Vehiculo vehiculo3 = new Vehiculo();
        vehiculo3.setMatricula("mat3");
        vehiculo3.setTag("tag3");
        vehiculo3.setNacionalidad(Nacionalidad.NACIONAL);

        gestionClienteService.vincularVehiculo("CI1", new DataVehiculo(vehiculo1));
        gestionClienteService.vincularVehiculo("CI2",new DataVehiculo(vehiculo2));
        gestionClienteService.vincularVehiculo("CI1",new DataVehiculo(vehiculo3));
        gestionClienteService.cargarSaldo("CI1", 1000.08);

    }



    // Otros métodos para insertar datos de prueba...

}
