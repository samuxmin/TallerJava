package org.tallerjava.moduloSucive.infraestructura.persistencia;

import java.time.LocalDate;
import java.util.List;

import org.tallerjava.moduloSucive.dominio.repositorio.DataTipoCobro;
import org.tallerjava.moduloSucive.dominio.repositorio.PasadaPorPeaje;
import org.tallerjava.moduloSucive.dominio.repositorio.RangoFechas;
import org.tallerjava.moduloSucive.dominio.repositorio.Vehiculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped

public class SuciveRepositorioImpl implements SuciveRepositorio {
    
    @PersistenceContext
    private EntityManager em;

    public SuciveRepositorioImpl() {

    }
  



    /***************************************************************************************** */
  
    @Transactional
    @Override
    public boolean crearPasadaPorPeaje(String matricula, double importe) {
        Vehiculo vehiculo = buscarVehiculoPorMatricula(matricula);
        if (vehiculo != null) {
            LocalDate fecha = LocalDate.now();
            double costo = importe;
            DataTipoCobro tipoCobro = DataTipoCobro.SUCIVE;
    
        
            // Crear una nueva pasada por peaje para el vehículo
            
            PasadaPorPeaje pasada = new PasadaPorPeaje(costo, fecha, tipoCobro, vehiculo);
            //vehiculo.getPasadasPorPeaje().add(pasada);

            em.persist(pasada);
            //em.merge(vehiculo);
                
        return true;
            
        }
        return false;
    }


  /***************************************************************************************** */

    @Override
    public List<PasadaPorPeaje> obtenerPasadasPorPeaje(Vehiculo vehiculo) {
        
            return em.createQuery(
                "SELECT p FROM PasadaPorPeajeSucive p JOIN p.vehiculoSucive v WHERE v = :vehiculoSucive AND p.tipoCobro = :tipoCobro",
                PasadaPorPeaje.class
            )
            .setParameter("vehiculo", vehiculo)
            .setParameter("tipoCobro", DataTipoCobro.SUCIVE)
            .getResultList();
        
    }
    
    /***************************************************************************************** */


    @Override
    public List<PasadaPorPeaje> obtenerPasadasEnRango(RangoFechas rangoFechas) {
        return em.createQuery(
            "SELECT p FROM PasadaPorPeajeSucive p WHERE p.fecha BETWEEN :fechaInicial AND :fechaFinal",
            PasadaPorPeaje.class
        )
        .setParameter("fechaInicial", rangoFechas.getFechainicial())
        .setParameter("fechaFinal", rangoFechas.getFechafinal())
        .getResultList();
    }


    @Override public Vehiculo buscarVehiculoPorMatricula(String matricula){
        
        return em.find(Vehiculo.class,matricula);
    }



    @Override
    public void addVehiculo(Vehiculo v){
        if(buscarVehiculoPorMatricula(v.getMatricula()) == null){
            em.persist(v);
        }else{
            em.merge(v);
        }
    }
}

