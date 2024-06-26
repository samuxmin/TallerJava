package org.tallerjava.moduloGestionCliente.aplicacion;

import java.time.LocalDate;
import java.util.List;

import org.tallerjava.moduloGestionCliente.dominio.clases.Cuenta;
import org.tallerjava.moduloGestionCliente.dominio.clases.PasadaPorPeaje;
import org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloGestionCliente.dominio.datatypes.DataVehiculo;

public interface GestionClienteService {
    public boolean altaClienteTelepeaje(Usuario usuario);
    //public boolean vincularVehiculo(Vehiculo vehiculo,Usuario usuario);
    public boolean desvincularVehiculo(String tag);
   // public List<Vehiculo> mostraVehiculosVinculados(Usuario usuario);

    public boolean cargarSaldo(String ci,double importe);
    public double consultarSaldo(Usuario usuario);
    public double consultarSaldo(String usr);
    public boolean asociarTarjeta(String ci, Tarjeta tarjeta);

    public List<PasadaPorPeaje> consultarPasadas(String ciUsuario, LocalDate fechaInicio, LocalDate fechaFin);
    public List<PasadaPorPeaje> consultarPasadas(String ciUssuario, String tagVehiculo , LocalDate fechaInicio, LocalDate fechaFin);

    public List<Cuenta> obtenerCuentasPorTag(String tag);

    public List<Cuenta> obtenerCuentasPorCI(String ci);
    public boolean realizarPrePago(double importe, String tag);
    public boolean realizarPostPago(double importe, String tag);
    public List<Vehiculo> mostraVehiculosVinculados(String ci);
    public boolean vincularVehiculo(DataVehiculo vehiculo, String ciUsr);
    public Usuario getUsuario(String ciUsr);
    public Vehiculo getVehiculoByTag(String tag);
    public void addPasada(PasadaPorPeaje pasada);
}
