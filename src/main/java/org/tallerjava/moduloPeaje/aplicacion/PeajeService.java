package org.tallerjava.moduloPeaje.aplicacion;

public interface PeajeService {
    public boolean estaHabilitado(String id); //puede venir tag o matrícula true: habilitado para pasar || false: no habilitado
    public void actualizarTarifaComun(double importe); // actualiza el costo de la tarifa común
    public void actualizarTarifaPreferencial(double preferencial); //actualiza el costo de la tarifa preferencial
}
