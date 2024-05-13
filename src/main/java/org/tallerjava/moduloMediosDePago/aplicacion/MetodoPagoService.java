package org.tallerjava.moduloMediosDePago.aplicacion;

import org.tallerjava.moduloMediosDePago.dominio.Usuario;
import org.tallerjava.moduloMediosDePago.dominio.Tarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;
import org.tallerjava.moduloMediosDePago.dominio.RangoFechas;
import org.tallerjava.moduloMediosDePago.dominio.Pagos;
import java.util.List;


public interface MetodoPagoService {

    public void altaCliente(Usuario usuario, Tarjeta tarjeta);
    public void notificarPago(Usuario usuario, Vehiculo vehiculo, double importe, Tarjeta tarjeta);
    public List<Pagos> consultaDePagosPorDia(RangoFechas rangoFechas);
    public List<Pagos> consultaDePagosPorCliente(Usuario usuario);
    public List<Pagos> consultaDePagosClienteVehiculo(Usuario usuario, Vehiculo vehiculo);

}
