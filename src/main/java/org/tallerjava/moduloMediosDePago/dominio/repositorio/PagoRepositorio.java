package org.tallerjava.moduloMediosDePago.dominio.repositorio;

import org.tallerjava.moduloMediosDePago.dominio.*;
import java.util.List;

public interface PagoRepositorio {
    public void registrarPago(Pago pago);
    public List<Pago> obtenerPagosPorRangoFechas(RangoFechas rangoFechas);
    public List<Pago> obtenerPagosPorUsuario(Usuario usuario);
    public List<Pago> obtenerPagosPorUsuarioYVehiculo(Usuario usuario, Vehiculo vehiculo);
    public boolean existeUsuario(String ci);
    public Usuario getUsuario(String ci);
    public void registrarUsuario(Usuario usuario);
}