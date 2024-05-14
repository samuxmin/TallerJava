package org.tallerjava.moduloPeaje.aplicacion;

public interface PeajeService {
    public boolean estaHabilitadoSincronico(String tag, String matricula);
    public void actualizarTarifaComun(double importe);
    public void actualizarTarifaPreferencial(double importe);
}
