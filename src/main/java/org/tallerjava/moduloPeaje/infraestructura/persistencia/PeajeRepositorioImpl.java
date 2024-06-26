package org.tallerjava.moduloPeaje.infraestructura.persistencia;

import org.tallerjava.moduloPeaje.dominio.PasadaPorPeajePeaje;
import org.tallerjava.moduloPeaje.dominio.TarifaComun;
import org.tallerjava.moduloPeaje.dominio.TarifaPreferencial;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PeajeRepositorioImpl implements PeajeRepositorio {

    @PersistenceContext
    private EntityManager em;

@Transactional
    @Override
    public Vehiculo findByTag(String tag) {
        return em.find(Vehiculo.class, tag);
    }

    @Override
    public Vehiculo findByMatricula(String matricula) {
        try {
            return em.createQuery("SELECT v FROM VehiculoPeaje v WHERE v.matricula = :matricula", Vehiculo.class)
                    .setParameter("matricula", matricula)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TarifaPreferencial obtenerTarifaPreferencial() {
        try {
            return em.createQuery("SELECT t FROM TarifaPreferencialPeaje t", TarifaPreferencial.class)
                    .getSingleResult();
        } catch (Exception e) {
            return new TarifaPreferencial(100);
        }
    }

    @Override
    public TarifaComun obtenerTarifaComun() {
        try {
            return em.createQuery("SELECT t FROM TarifaComunPeaje t", TarifaComun.class)
                    .getSingleResult();
        } catch (Exception e) {
            return new TarifaComun(150);
        }
    }

    @Override
    public void addVehiculo(Vehiculo v) {

        Vehiculo vBD = findByTag(v.getTag());

        if (vBD == null) {
            System.out.println("NO Existe");
            try {
                em.persist(v);
            } catch (Exception e) {
                //XD
            }
        }

    }

    @Transactional
    @Override
    public TarifaPreferencial setTarifaPreferencial(double monto){
        Query query = em.createQuery("delete FROM TarifaPreferencial");
        query.executeUpdate();
        TarifaPreferencial t = new TarifaPreferencial(monto);
        em.persist(t);
        return t;
    }

    @Transactional
    @Override
    public TarifaComun setTarifaComun(double monto){
        Query query = em.createQuery("delete FROM TarifaComun");
        query.executeUpdate();
        TarifaComun t = new TarifaComun(monto);
        em.persist(t);
        return t;
    }

    @Transactional
    @Override
    public void addPasada(PasadaPorPeajePeaje pasada){
        em.persist(pasada);
    }
}
