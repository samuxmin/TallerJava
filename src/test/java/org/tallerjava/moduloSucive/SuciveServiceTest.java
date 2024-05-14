package org.tallerjava.moduloSucive;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.tallerjava.moduloSucive.aplicacion.SuciveService;
import org.tallerjava.moduloSucive.aplicacion.SuciveServiceImpl;
import org.tallerjava.moduloSucive.dominio.repositorio.SuciveRepositorio;
import org.tallerjava.moduloSucive.dominio.repositorio.Usuario;

public class SuciveServiceTest {
    private static SuciveService suciveService;
    private static SuciveRepositorio mockRepositorio;

    @BeforeAll
    public static void setUp() {
        mockRepositorio = mock(SuciveRepositorio.class);
        suciveService = new SuciveServiceImpl();
        ((SuciveServiceImpl) suciveService).setRepo(mockRepositorio); // InyecciÃ³n del mock
    }

    @Test
    public void testNotificarPago_ConUsuarioExistente_DeberiaRetornarTrue() {
        // Arrange
        String matricula = "ABC123";
        double importe = 20.0;
        Usuario usuarioExistente = new Usuario("correo@example.com", "Facundo", "12345678", null, null);
        when(mockRepositorio.buscarUsuarioPorMatricula(matricula)).thenReturn(usuarioExistente);

        // Act
        boolean resultado = suciveService.notificarPago(matricula, importe);

        // Assert
        assertTrue(resultado);
        verify(mockRepositorio, times(1)).crearPasadaPorPeaje(usuarioExistente, importe);
    }

    @Test
    public void testNotificarPago_ConUsuarioNoExistente_DeberiaRetornarFalse() {
        // Arrange
        String matricula = "XYZ789";
        double importe = 30.0;
        when(mockRepositorio.buscarUsuarioPorMatricula(matricula)).thenReturn(null);

        // Act
        boolean resultado = suciveService.notificarPago(matricula, importe);

        // Assert
        assertFalse(resultado);
        verify(mockRepositorio, never()).crearPasadaPorPeaje(any(), anyDouble()); // Verifica que no se llama a crearPasadaPorPeaje
    }
}
