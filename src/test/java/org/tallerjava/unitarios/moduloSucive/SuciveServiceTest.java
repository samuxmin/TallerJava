package org.tallerjava.unitarios.moduloSucive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tallerjava.moduloSucive.dominio.repositorio.DataTipoCobro;
import org.tallerjava.moduloSucive.dominio.repositorio.Matricula;
import org.tallerjava.moduloSucive.dominio.repositorio.PasadaPorPeaje;
import org.tallerjava.moduloSucive.dominio.repositorio.RangoFechas;
import org.tallerjava.moduloSucive.dominio.repositorio.Usuario;
import org.tallerjava.moduloSucive.dominio.repositorio.Vehiculo;
import org.tallerjava.moduloSucive.dominio.repositorio.VehiculoNacional;
import org.tallerjava.moduloSucive.dominio.repositorio.Vinculo;
import org.tallerjava.moduloSucive.infraestructura.persistencia.SuciveRepositorioImpl;

public class SuciveServiceTest {
    private SuciveRepositorioImpl repo;
    private VehiculoNacional vehiculoNacional;
    private LocalDate fechaFija;

    @BeforeEach
    public void setup() {
        repo = new SuciveRepositorioImpl();
        fechaFija = LocalDate.of(2023, 5, 1);
        List<PasadaPorPeaje> pasadasPorPeaje = new ArrayList<>();
        Vinculo vinculo = new Vinculo(true, LocalDate.now(), new Vehiculo(pasadasPorPeaje, null));
        Matricula matricula = new Matricula("ABCD");
        vehiculoNacional = new VehiculoNacional(pasadasPorPeaje, vinculo, matricula, null);
        vinculo.setVehiculo(vehiculoNacional);  // Asegurarse de que el vinculo tenga una referencia a vehiculoNacional
        Usuario usuario = new Usuario("correo@example.com", "Facundo", "12345678", new ArrayList<>(), null);
        usuario.getVinculosVehiculos().add(vinculo);     
        PasadaPorPeaje pasada1 = new PasadaPorPeaje(100.0, fechaFija.minusDays(1), DataTipoCobro.Tipo.SUCIVE, vehiculoNacional);
        PasadaPorPeaje pasada2 = new PasadaPorPeaje(50.0, fechaFija.plusDays(1), DataTipoCobro.Tipo.SUCIVE, vehiculoNacional);
        pasadasPorPeaje.add(pasada1);
        pasadasPorPeaje.add(pasada2);
        vehiculoNacional.setPasadasPorPeaje(pasadasPorPeaje);   
        vinculo.setVehiculo(vehiculoNacional);
        repo.addUsuario(usuario);
    }
//********************************************************************************************************** */
    @Test
    public void testBuscarUsuarioPorMatricula_Existente() {
        String matricula = "ABCD";
        Usuario resultado = repo.buscarUsuarioPorMatricula(matricula);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("correo@example.com", resultado.getEmail());
        Assertions.assertEquals("Facundo", resultado.getNombre());
        Assertions.assertEquals("12345678", resultado.getCi());
    }
//**********************************************************************************************************
    @Test
    public void testBuscarUsuarioPorMatricula_NoExistente() {
        String matricula = "XYZ";
        Usuario resultado = repo.buscarUsuarioPorMatricula(matricula);
        Assertions.assertNull(resultado);
    }
//**********************************************************************************************************
/* 
@Test
public void testCrearPasadaPorPeaje() {
    double importe = 100.0;
    Usuario usuario = repo.usr.get(0);  // Obtén el usuario creado en el setup
    LocalDate fechaTest = fechaFija.plusDays(2); // Utilizar una fecha fija para el test
    repo.crearPasadaPorPeaje(usuario, importe);

    for (Vinculo vinculo : usuario.getVinculosVehiculos()) {
        Vehiculo vehiculo = vinculo.getVehiculo();
        if (vehiculo instanceof VehiculoNacional) {
            List<PasadaPorPeaje> pasadasPorPeaje = vehiculo.getPasadasPorPeaje();
            Assertions.assertFalse(pasadasPorPeaje.isEmpty(), "La lista de pasadas por peaje no debe estar vacía.");
            PasadaPorPeaje pasada = pasadasPorPeaje.get(pasadasPorPeaje.size() - 1); // Obtener la última pasada
            Assertions.assertEquals(importe, pasada.getCosto());
            Assertions.assertEquals(fechaTest, pasada.getFecha()); // Usar la fecha fija
            Assertions.assertEquals(DataTipoCobro.Tipo.SUCIVE, pasada.getTipoCobro());
            Assertions.assertEquals(vehiculo, pasada.getVehiculo());
        }
    }
}
*/
//**********************************************************************************************************
    
    @Test
    public void testObtenerPasadasPorPeaje() {
        List<PasadaPorPeaje> resultado = repo.obtenerPasadasPorPeaje(vehiculoNacional);
        Assertions.assertNotNull(resultado, "El resultado no debe ser nulo.");
        Assertions.assertEquals(2, resultado.size(), "Debe haber una pasada por peaje con tipo SUCIVE.");
        Assertions.assertEquals(DataTipoCobro.Tipo.SUCIVE, resultado.get(0).getTipoCobro(), "El tipo de cobro debe ser SUCIVE.");
    }

//**********************************************************************************************************

    @Test
    public void testObtenerPasadasEnRango() {
        LocalDate fechaInicial = fechaFija.minusDays(2);
        LocalDate fechaFinal = fechaFija;
        RangoFechas rangoFechas = new RangoFechas(fechaInicial, fechaFinal);

        List<PasadaPorPeaje> resultado = repo.obtenerPasadasEnRango(rangoFechas);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals(fechaFija.minusDays(1), resultado.get(0).getFecha());
    }
//**********************************************************************************************************
    @Test
    public void testCalcularImportesPorDia() {
        List<PasadaPorPeaje> pasadasPorPeaje = new ArrayList<>();
        pasadasPorPeaje.add(new PasadaPorPeaje(100.0, fechaFija.minusDays(1), DataTipoCobro.Tipo.SUCIVE, vehiculoNacional));
        pasadasPorPeaje.add(new PasadaPorPeaje(50.0, fechaFija.minusDays(1), DataTipoCobro.Tipo.SUCIVE, vehiculoNacional));
        pasadasPorPeaje.add(new PasadaPorPeaje(75.0, fechaFija, DataTipoCobro.Tipo.SUCIVE, vehiculoNacional));

        Map<LocalDate, Double> resultado = repo.calcularImportesPorDia(pasadasPorPeaje);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(150.0, resultado.get(fechaFija.minusDays(1)));
        Assertions.assertEquals(75.0, resultado.get(fechaFija));
    }
//**********************************************************************************************************
    @Test
    public void testConvertirAMapas() {
        Map<LocalDate, Double> importesPorDia = new HashMap<>();
        importesPorDia.put(fechaFija.minusDays(1), 150.0);
        importesPorDia.put(fechaFija, 75.0);

        List<PasadaPorPeaje> resultado = repo.convertirAMapas(importesPorDia);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.size());

        PasadaPorPeaje pasada1 = resultado.get(0);
        PasadaPorPeaje pasada2 = resultado.get(1);

        if (pasada1.getFecha().equals(fechaFija.minusDays(1))) {
            Assertions.assertEquals(150.0, pasada1.getCosto());
            Assertions.assertEquals(75.0, pasada2.getCosto());
            Assertions.assertEquals(fechaFija, pasada2.getFecha());
        } else {
            Assertions.assertEquals(75.0, pasada1.getCosto());
            Assertions.assertEquals(150.0, pasada2.getCosto());
            Assertions.assertEquals(fechaFija.minusDays(1), pasada2.getFecha());
        }
    }
}
