package org.tallerjava.moduloMediosDePago.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.tallerjava.moduloMediosDePago.aplicacion.MetodoPagoServiceImpl;
import org.tallerjava.moduloMediosDePago.dominio.RangoFechas;
import org.tallerjava.moduloMediosDePago.dominio.Tarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Usuario;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;
import org.tallerjava.moduloMediosDePago.dominio.Vinculo;
import org.tallerjava.moduloMediosDePago.dominio.ClienteTelepeaje;
import org.tallerjava.moduloMediosDePago.dominio.Pagos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoServiceImplTest {

    private MetodoPagoServiceImpl metodoPagoService;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private RangoFechas rangoFechas;
    

    @Test
    public void testAltaCliente() {

    }

    @Test
    public void testConsultaDePagosPorDia() {
        
    }
    
    @Test
    public void testConsultaDePagosPorCliente() {

    }
    
    @Test
    public void testConsultaDePagosClienteVehiculo() {

    }
}