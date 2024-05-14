package org.tallerjava.moduloGestionCliente.aplicacion;

import org.tallerjava.moduloGestionCliente.dominio.clases.*;

import java.time.LocalDate;
import java.util.List;

public interface GestionClienteService {
    public void altaClienteTelepeaje(Usuario usuario);
    public void vincularVehiculo(Vehiculo vehiculo,Usuario usuario);
    public void desvincularVehiculo(Usuario usr, Vehiculo v);
    public List<Vehiculo> mostraVehiculosVinculados(Usuario usuario);
    public void cargarSaldo(Usuario usuario,float importe);
    public double consultarSaldo(Usuario usuario);
    public void asociarTarjeta(Usuario usuario, Tarjeta tarjeta);
    public List<PasadaPorPeaje> consultarPasadas(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin);
    public List<PasadaPorPeaje> consultarPasadas(Usuario usuario, Vehiculo vehiculo , LocalDate fechaInicio, LocalDate fechaFin);
    public List<Cuenta> obtenerCuentasPorTag(Tag tag);
    public boolean realizarPrePago(double importe, String tag);
    public boolean realizarPostPago(double importe, String tag);
}
