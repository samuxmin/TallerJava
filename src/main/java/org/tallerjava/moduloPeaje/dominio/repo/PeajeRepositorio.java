package org.tallerjava.moduloPeaje.dominio.repo;

import org.tallerjava.moduloPeaje.dominio.PasadaPorPeajePeaje;
import org.tallerjava.moduloPeaje.dominio.TarifaComun;
import org.tallerjava.moduloPeaje.dominio.TarifaPreferencial;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;

/**
 * Eventualmente, si esta clase crece mucho, puedo tener más de un repositorip
 */
public interface PeajeRepositorio {
    public Vehiculo findByTag(String tag);
    public Vehiculo findByMatricula(String matricula);
    public TarifaPreferencial obtenerTarifaPreferencial();
    public TarifaComun obtenerTarifaComun();
    public TarifaPreferencial setTarifaPreferencial(double monto);
    public TarifaComun setTarifaComun(double monto);
    public void addPasada(PasadaPorPeajePeaje pasada);
    public void addVehiculo(Vehiculo vehiculo);
}
