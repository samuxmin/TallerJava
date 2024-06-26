package org.tallerjava.moduloMediosDePago.intraestructura.persistencia;

import java.util.List;

import org.tallerjava.moduloMediosDePago.dominio.ClienteTelepeaje;
import org.tallerjava.moduloMediosDePago.dominio.CuentaPOSTPaga;
import org.tallerjava.moduloMediosDePago.dominio.CuentaPREPaga;
import org.tallerjava.moduloMediosDePago.dominio.Pago;
import org.tallerjava.moduloMediosDePago.dominio.RangoFechas;
import org.tallerjava.moduloMediosDePago.dominio.Tarjeta;
import org.tallerjava.moduloMediosDePago.dominio.Usuario;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;
import org.tallerjava.moduloMediosDePago.dominio.repositorio.PagoRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Default
@ApplicationScoped
public class PagoRepositorioImpl implements PagoRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    /* 
    @Override
    @Transactional
    public void registrarPago(Pago pago) {
        entityManager.persist(pago);
    }
    */

@Override
@Transactional
public void registrarPago(Pago pago) {
    try {
  
        Vehiculo vehiculo = pago.getVehiculo();
        if (vehiculo != null && entityManager.find(Vehiculo.class, vehiculo.getTag()) == null){
            entityManager.persist(vehiculo);
        }

        entityManager.persist(pago);
    } catch (Exception e) {
        System.err.println("Error al persistir el pago: " + e.getMessage());
        throw e;
    }
}


    @Override
    @Transactional
    public List<Pago> obtenerPagosPorRangoFechas(RangoFechas rangoFechas) {
        return entityManager.createQuery("SELECT p FROM PagoPago p WHERE p.fecha BETWEEN :fechaInicial AND :fechaFinal", Pago.class)
                .setParameter("fechaInicial", rangoFechas.getFechaInicial())
                .setParameter("fechaFinal", rangoFechas.getFechaFinal())
                .getResultList();
    }

    @Override
    @Transactional
    public List<Pago> obtenerPagosPorUsuario(Usuario usuario) {
        return entityManager.createQuery("SELECT p FROM PagoPago p WHERE p.usuario = :usuario", Pago.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Pago> obtenerPagosPorUsuarioYVehiculo(Usuario usuario, Vehiculo vehiculo) {
        return entityManager.createQuery("SELECT p FROM PagoPago p WHERE p.usuario = :usuario AND p.vehiculo = :vehiculo", Pago.class)
                .setParameter("usuario", usuario)
                .setParameter("vehiculo", vehiculo)
                .getResultList();
    }

    @Override
    public boolean existeUsuario(String ci) {
        if(getUsuario(ci) != null){
            return true;
        }
        return false;
    }

    @Override
    public Usuario getUsuario(String ci) {
        return entityManager.find(Usuario.class, ci);
    }


    @Override
    @Transactional
    public void registrarUsuario(Usuario usuario) {

        Usuario usuarioExistente = entityManager.find(Usuario.class, usuario.getCi());
        Tarjeta tarjetaExistente = entityManager.find(Tarjeta.class, usuario.getClienteTelepeaje().getCuentaPostPaga().getTarjeta().getNro());
    
    
        ClienteTelepeaje clienteTelepeaje = usuario.getClienteTelepeaje();
        if (clienteTelepeaje != null) {
            CuentaPOSTPaga cuentaPostPaga = clienteTelepeaje.getCuentaPostPaga();
            if (cuentaPostPaga != null) {
                Tarjeta tarjeta = cuentaPostPaga.getTarjeta();
                if (tarjetaExistente != null) {
                    entityManager.merge(tarjeta);
                }else{
                    entityManager.persist(tarjeta);
                }
                entityManager.persist(cuentaPostPaga);
            }
            CuentaPREPaga cuentaPrePaga = clienteTelepeaje.getCuentaPrePaga();
            if (cuentaPrePaga != null) {
                entityManager.persist(cuentaPrePaga);
            }
            entityManager.persist(clienteTelepeaje);
        }
        if(usuarioExistente != null){
            entityManager.merge(usuario);
        }
        entityManager.persist(usuario);
    }
    
    
    
}


