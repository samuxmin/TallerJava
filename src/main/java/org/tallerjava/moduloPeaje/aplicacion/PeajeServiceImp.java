
package org.tallerjava.moduloPeaje.aplicacion;
import jakarta.enterprise.context.ApplicationScoped;
import org.tallerjava.moduloPeaje.dominio.Tarifa;
import org.tallerjava.moduloPeaje.dominio.TarifaComun;
import org.tallerjava.moduloPeaje.dominio.TarifaPreferencial;
import org.tallerjava.moduloPeaje.dominio.VehiculoNacional;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.dominio.Vinculo;
import org.tallerjava.moduloPeaje.dominio.Tag;
import org.tallerjava.moduloPeaje.dominio.Matricula;
import org.tallerjava.moduloPeaje.dominio.PasadaPorPeaje;

import org.tallerjava.moduloPeaje.dominio.VehiculoExtranjero;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class PeajeServiceImp implements PeajeService {

    private List<Vehiculo> vehiculos;
    private List<PasadaPorPeaje> pasadas;
    TarifaPreferencial tarifaPref;
    TarifaComun tarifaComun;

    public PeajeServiceImp() {
        this.vehiculos = new ArrayList<>();
        this.pasadas = new ArrayList<>();

        this.tarifaPref= new TarifaPreferencial(800);
        this.tarifaComun= new TarifaComun(1000);

        Matricula mat1 = new Matricula("mat1");
        Matricula mat2 = new Matricula("mat2");

        Tag tag1 = new Tag("1");
        Tag tag2 = new Tag("2");

        VehiculoNacional vehiculoNac = new VehiculoNacional(pasadas,null,mat1,tag1);
        VehiculoExtranjero vehiculoExt = new VehiculoExtranjero(pasadas,null,tag2);

        Vinculo v1 = new Vinculo(true, LocalDate.parse("2024-04-16"), vehiculoNac);
        Vinculo v2 = new Vinculo(true, LocalDate.parse("2024-03-18"), vehiculoExt);

        vehiculoNac.setVinculo(v1);
        vehiculoExt.setVinculo(v2);

        this.vehiculos.add(vehiculoNac);
        this.vehiculos.add(vehiculoExt);
    }

    @Override
    public boolean estaHabilitado(String id) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof VehiculoNacional) {
                if (((VehiculoNacional)vehiculo).getMatricula().getNroMatricula().equals(id)) {
                    return true; // El vehículo está habilitado
                }
            }
            if (vehiculo instanceof VehiculoExtranjero) {
                if (((VehiculoExtranjero)vehiculo).getTag().getId().equals(id)) {
                    return true; // El vehículo está habilitado
                }
            }
        }
        return false; // El vehículo no está habilitado
    }

    @Override
    public void actualizarTarifaComun(double importe) {
        tarifaComun.setMonto(importe);
    }

    @Override
    public void actualizarTarifaPreferencial(double preferencial) {
        tarifaPref.setMonto(preferencial);
    }
}
