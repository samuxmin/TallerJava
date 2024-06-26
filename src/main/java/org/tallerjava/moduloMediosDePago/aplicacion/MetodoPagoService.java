package org.tallerjava.moduloMediosDePago.aplicacion;

import org.tallerjava.moduloGestionCliente.interfase.evento.out.AsociarTarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Usuario;
import org.tallerjava.moduloMediosDePago.dominio.Tarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;

import jakarta.ws.rs.core.Response;

import org.tallerjava.moduloMediosDePago.dominio.RangoFechas;
import org.tallerjava.moduloMediosDePago.dominio.Pago;
import java.util.List;

public interface MetodoPagoService {
    public boolean altaCliente(AsociarTarjeta asociarTarjeta);
    public boolean notificarPago(String ci, String matricula, double importe, long idTarjeta);
    public List<Pago> consultaDePagosPorDia(RangoFechas rangoFechas);
    public List<Pago> consultaDePagosPorCliente(Usuario usuario);
    public List<Pago> consultaDePagosClienteVehiculo(Usuario usuario, Vehiculo vehiculo);
}


