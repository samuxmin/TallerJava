package org.tallerjava.moduloSucive.aplicacion;

import java.util.ArrayList;
import java.util.List;

import org.tallerjava.moduloSucive.dominio.repositorio.PasadaPorPeaje;
import org.tallerjava.moduloSucive.dominio.repositorio.RangoFechas;
import org.tallerjava.moduloSucive.dominio.repositorio.Vehiculo;
import org.tallerjava.moduloSucive.infraestructura.persistencia.SuciveRepositorio;
import org.tallerjava.moduloSucive.interfase.api.PagoRequest;
import org.tallerjava.moduloSucive.interfase.api.SuciveController;
import org.tallerjava.moduloSucive.interfase.api.classes.PagoResponse;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
//import org.tallerjava.unitarios.moduloSucive.SuciveSoapMock;

@ApplicationScoped
public class SuciveServiceImpl implements SuciveService {

    @Inject
    @Getter
    @Setter
    private SuciveRepositorio repo;

    @Inject
    SuciveController suciveController;
    @PostConstruct
    private void inicializar() {
        // Inicializar cualquier configuración necesaria aquí
    }

    // Instancia del mock del servicio SOAP de Sucive
    //private SuciveSoapMock suciveSoapMock = new SuciveSoapMock();


    
    @Transactional
    @Override
    public boolean notificarPago(String matricula, double importe) {
        Vehiculo vehiculo = repo.buscarVehiculoPorMatricula(matricula);
        if (vehiculo == null) {
            vehiculo = new Vehiculo();
            vehiculo.setMatricula(matricula);
            repo.addVehiculo(vehiculo);
        }
            // Utilizar el mock para simular el pago a Sucive
            PagoRequest request = new PagoRequest();
            request.setMatricula(matricula);
            
            //TODO POR AHORA LO DEJAMOS ASI :V:V:vvvXDxdxd
            // cambiar todo a double
            request.setMonto( (int) importe);
            PagoResponse response = suciveController.realizarPago(request);
            boolean pagoRealizado = response.isExito();//suciveSoapMock.realizarPago(matricula, importe);
            if (pagoRealizado) {
                repo.crearPasadaPorPeaje(matricula, importe);
                return true;
            }
        
        return false;
    }

    
    @Override
public List<PasadaPorPeaje> consultaDePagos(String matricula) {
    Vehiculo vehiculo = repo.buscarVehiculoPorMatricula(matricula);
    
    if(vehiculo != null){
        return vehiculo.getPasadasPorPeaje();
    } else {
        return new ArrayList<>();
    }
}

@Override
public List<PasadaPorPeaje> consultaDePagos(RangoFechas rangoFechas) {
    return repo.obtenerPasadasEnRango(rangoFechas);

}
@Override
public void addVehiculo(Vehiculo v){
    repo.addVehiculo(v);
    
    
}
/* 
    @Override
    public List<PasadaPorPeaje> consultaDePagos(String matricula) {
        List<PasadaPorPeaje> pasadas = new ArrayList<>();
        Usuario usuario = repo.buscarUsuarioPorMatricula(matricula);
        if (usuario != null) {
            for (Vinculo vinculo : usuario.getVinculosVehiculos()) {
                Vehiculo vehiculo = vinculo.getVehiculo();
                pasadas.addAll(repo.obtenerPasadasPorPeaje(vehiculo));
            }
        }
        return pasadas;
    }

    @Override
    public List<PasadaPorPeaje> consultaDePagos(RangoFechas rangoFechas) {
        List<PasadaPorPeaje> pasadasEnRango = repo.obtenerPasadasEnRango(rangoFechas);
        Map<LocalDate, Double> importesPorDia = repo.calcularImportesPorDia(pasadasEnRango);
        return repo.convertirAMapas(importesPorDia);
    }
*/
    /********************************************************************* */
    
}
