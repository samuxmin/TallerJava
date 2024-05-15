package org.tallerjava.moduloComunicacion.aplicacion;

import org.tallerjava.moduloGestionCliente.dominio.eventos.SaldoInsuficiente;

import java.util.List;

public interface ComunicacionService {
    public void notificarSaldoInsuficiente(SaldoInsuficiente si);
    public List<String> getNotificaciones(String ci);
    public void notificarInformacion(String ci, String informacion);
}
