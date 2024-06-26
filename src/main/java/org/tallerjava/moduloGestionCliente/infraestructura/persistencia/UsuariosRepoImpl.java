package org.tallerjava.moduloGestionCliente.infraestructura.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.tallerjava.moduloGestionCliente.dominio.clases.PasadaPorPeaje;
import org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vinculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuariosRepoImpl implements UsuariosRepo {

    // private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TallerJava#tallerjavaPU");
    @PersistenceContext
    private EntityManager em;//= emf.createEntityManager();

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public UsuariosRepoImpl() {

    }

    @Override
    public boolean vincularVehiculo(Vinculo vinculo) {
        String tag = vinculo.getVehiculo().getTag();
        if (getVehiculoByTag(tag) == null) {
            em.persist(vinculo.getVehiculo());
            em.persist(vinculo);
            return true;
        }
        Vinculo vinculoBD = getVinculoByTag(tag);
        if (vinculoBD != null) {
            vinculoBD.setActivo(true);
            em.merge(vinculoBD);
        } else {
            em.persist(vinculo);
        }
        return true;
    }

    public Vinculo getVinculoByTag(String tag) {
        TypedQuery<Vinculo> query = em.createQuery("SELECT v FROM VinculoGestion v WHERE v.vehiculo.tag = :tag", Vinculo.class);
        query.setParameter("tag", tag);
        Vinculo vinculo = query.getSingleResult();
        return vinculo;
    }

    @Override
    public boolean desvincularVehiculo(String tag) {
        try {
            Vinculo vinculo = getVinculoByTag(tag);
            System.out.println("tag: " + tag);

            vinculo.setActivo(false);
            em.merge(vinculo);

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void addUsuario(Usuario u) {
        em.persist(u);
    }

    @Override
    public Usuario getUsuarioByTag(String tag) {

        String ci = em.createQuery("SELECT v.vinculo.usuario.ci FROM VehiculoGestion v WHERE v.tag = :tag", String.class)
                .setParameter("tag", tag)
                .getSingleResult();

        return em.find(Usuario.class, ci);
    }

    @Override
    public Vehiculo getVehiculoByTag(String tag) {
        return em.find(Vehiculo.class, tag);
    }

    @Override
    public Usuario getUsuarioByCI(String ci) {
        return em.find(Usuario.class, ci);
    }

    @Override
    public List<Vehiculo> getVehiculosUsr(String ci) {
        TypedQuery<Vinculo> query = em.createQuery("SELECT vi FROM VinculoGestion vi WHERE vi.usuario.ci = :ci AND activo=1", Vinculo.class);
        query.setParameter("ci", ci);
        List<Vinculo> vinculos = query.getResultList();
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Vinculo v : vinculos) {
            vehiculos.add(v.getVehiculo());
        }
        return vehiculos;
    }

    @Override
    @Transactional
    public boolean updateSaldo(Usuario usr) {
        try {
            System.out.println("saldo nuevo: " + usr.getSaldo() + "para la ci " + usr.getCi());
            

            em.merge(usr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<PasadaPorPeaje> getPasadasVehiculo(String tagVehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        Query query = em.createNativeQuery("SELECT * FROM PasadaPorPeajeGestion p WHERE p.vehiculo_tag = :tag AND p.fecha BETWEEN :fechaInicio AND :fechaFin", PasadaPorPeaje.class);
        query.setParameter("tag", tagVehiculo);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        List<PasadaPorPeaje> pasadas = query.getResultList();
        return pasadas;
    }

    @Override
    public List<PasadaPorPeaje> getPasadasUsr(String ciUsr, LocalDate fechaInicio, LocalDate fechaFin) {
        TypedQuery query = em.createQuery("SELECT p FROM PasadaPorPeajeGestion p WHERE p.vehiculo.vinculo.usuario.ci = :ci AND p.fecha BETWEEN :fechaInicio AND :fechaFin", PasadaPorPeaje.class);
        query.setParameter("ci", ciUsr);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        List<PasadaPorPeaje> pasadas = query.getResultList();
        return pasadas;
    }

    @Override
    public boolean asociarTarjeta(Usuario usuario, Tarjeta tarjeta) {
        try {

            if (getTarjetaByNro(tarjeta.getNro()) == null) {
                em.persist(tarjeta);
            }
            usuario.setTarjeta(tarjeta);
            em.merge(usuario);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Tarjeta getTarjetaByNro(long nro) {
        return em.find(Tarjeta.class, nro);
    }
    public void addPasada(PasadaPorPeaje pasada){
        em.persist(pasada);
    }
}
