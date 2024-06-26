package org.tallerjava.moduloPeaje.aplicacion;

import org.tallerjava.moduloPeaje.dominio.Vehiculo;

public interface PeajeService {
    public boolean estaHabilitadoSincronico(String tag, String matricula);
    public void actualizarTarifaComun(double importe);
    public void actualizarTarifaPreferencial(double importe);
    public void addVehiculo(Vehiculo vehiculo);
    public boolean procesarVehiculoNacional(String tag, String matricula); 
}
