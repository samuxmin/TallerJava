package org.tallerjava.moduloGestionCliente.infraestructura.persistencia;

import java.time.LocalDate;
import java.util.List;

import org.tallerjava.moduloGestionCliente.dominio.clases.PasadaPorPeaje;
import org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vinculo;

public interface UsuariosRepo {
    void addUsuario(Usuario u);
    Vehiculo getVehiculoByTag(String tag);
    Usuario getUsuarioByTag(String tag);
    Usuario getUsuarioByCI(String ci);
    boolean vincularVehiculo(Vinculo vinculo);
    List<Vehiculo> getVehiculosUsr(String ci);
    boolean updateSaldo(Usuario usr);
    boolean desvincularVehiculo(String tag);
    List<PasadaPorPeaje> getPasadasVehiculo(String tagVehiculo,LocalDate fechaInicio,LocalDate fechaFin);
    List<PasadaPorPeaje> getPasadasUsr(String ciUsr,LocalDate fechaInicio,LocalDate fechaFin);
    boolean asociarTarjeta(Usuario usuario, Tarjeta tarjeta);
    Tarjeta getTarjetaByNro(long nro);
    public void addPasada(PasadaPorPeaje pasada);
}