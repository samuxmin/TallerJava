package org.tallerjava.moduloMediosDePago.intraestructura.persistencia;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import org.tallerjava.moduloMediosDePago.dominio.*;
import org.tallerjava.moduloMediosDePago.dominio.repositorio.PagoRepositorio;
import java.util.ArrayList;
import java.util.List;
@Default
@ApplicationScoped
public class PagoRepositorioImpl implements PagoRepositorio {
    private List<Pago> pagos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void registrarPago(Pago pago) {
        pagos.add(pago);
    }

    @Override
    public List<Pago> obtenerPagosPorRangoFechas(RangoFechas rangoFechas) {
        List<Pago> pagosFiltrados = new ArrayList<>();

        for (Pago pago : pagos) {
            if (!pago.getFecha().isBefore(rangoFechas.getFechaInicial()) &&
                !pago.getFecha().isAfter(rangoFechas.getFechaFinal())) {
                pagosFiltrados.add(pago);
            }
        }

        return pagosFiltrados;
    }

    @Override
    public List<Pago> obtenerPagosPorUsuario(Usuario usuario) {
        List<Pago> pagosFiltrados = new ArrayList<>();

        for (Pago pago : pagos) {
            if (pago.getUsuario().equals(usuario)) {
                pagosFiltrados.add(pago);
            }
        }

        return pagosFiltrados;
    }

    @Override
    public List<Pago> obtenerPagosPorUsuarioYVehiculo(Usuario usuario, Vehiculo vehiculo) {
        List<Pago> pagosFiltrados = new ArrayList<>();

        for (Pago pago : pagos) {
            if (pago.getUsuario().equals(usuario) && pago.getVehiculo().equals(vehiculo)) {
                pagosFiltrados.add(pago);
            }
        }

        return pagosFiltrados;
    }

    @Override
    public boolean existeUsuario(String ci) {
        return (getUsuario(ci) != null);
    }

    @Override
    public Usuario getUsuario(String ci) {

        for (Usuario usuario : usuarios) {
            if (usuario.getCi() == ci) {
                return usuario;
            }
        }
        return null;
    }
    @Override
    public void registrarUsuario(Usuario usuario) {

        usuarios.add(usuario);
    }
}