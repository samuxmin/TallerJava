package org.tallerjava.moduloGestionCliente.aplicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.tallerjava.moduloGestionCliente.dominio.clases.Cuenta;
import org.tallerjava.moduloGestionCliente.dominio.clases.CuentaPOSTPaga;
import org.tallerjava.moduloGestionCliente.dominio.clases.CuentaPREPaga;
import org.tallerjava.moduloGestionCliente.dominio.clases.Nacionalidad;
import org.tallerjava.moduloGestionCliente.dominio.clases.PasadaPorPeaje;
import org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vinculo;
import org.tallerjava.moduloGestionCliente.dominio.datatypes.DataVehiculo;
import org.tallerjava.moduloGestionCliente.infraestructura.persistencia.UsuariosRepo;
import org.tallerjava.moduloGestionCliente.infraestructura.seguridad.HashFunctionUtil;
import org.tallerjava.moduloGestionCliente.interfase.evento.out.AsociarTarjeta;
import org.tallerjava.moduloGestionCliente.interfase.evento.out.PublicadorEventoGestion;
import org.tallerjava.moduloMediosDePago.aplicacion.MetodoPagoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Default
@ApplicationScoped
public class GestionClienteServiceImpl implements GestionClienteService {

    @Inject
    private UsuariosRepo usuariosRepo;

    int idCuentas = 0;
    @Inject
    private MetodoPagoService pagos;

    @Inject
    PublicadorEventoGestion evento;


    @Inject
    private Event<AsociarTarjeta> asociarTarjetaEvent;

    @Override
    @Transactional
    public boolean altaClienteTelepeaje(Usuario usuario) {
        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            System.out.println("emmpty password");
            return false;
        }else{
            usuario.setPassword(HashFunctionUtil.convertToHas( usuario.getPassword()));
        }

        if(usuariosRepo.getUsuarioByCI(usuario.getCi()) != null){
            return false;
        }
        usuariosRepo.addUsuario(usuario);
        return true;
    }

    @Override
    @Transactional
    public boolean vincularVehiculo(DataVehiculo dataVehiculo, String ciUsr) {
        Usuario usuario = usuariosRepo.getUsuarioByCI(ciUsr);

        if (usuario == null) {
            System.out.println("usr null");
            return false;
        }
        Vehiculo vehiculo = null;
        String tag = dataVehiculo.getTag();

        if (!dataVehiculo.isExtranjero()) {
            String matricula = dataVehiculo.getMatricula();
            vehiculo = new Vehiculo(null, null, tag, matricula, Nacionalidad.NACIONAL);

        } else {
            vehiculo = new Vehiculo(null, null, tag, "", Nacionalidad.EXTRANJERO);
        }
        Vinculo nuevoVinculo = new Vinculo(true, LocalDate.now(), vehiculo, usuario);
        vehiculo.setVinculo(nuevoVinculo);
        usuario.getVinculosVehiculos().add(nuevoVinculo);

        
        String nacionalidad = vehiculo.getNacionalidad().toString();
  
        if(usuariosRepo.getVehiculoByTag(tag) == null){
            evento.publicarNuevoVehiculo(vehiculo);
        }
        
        return usuariosRepo.vincularVehiculo(nuevoVinculo);
    }

    @Override
    @Transactional
    public boolean  desvincularVehiculo(String tag) {
        return usuariosRepo.desvincularVehiculo(tag);
    }

    @Override
    public List<Vehiculo> mostraVehiculosVinculados(String ci) {

        return usuariosRepo.getVehiculosUsr(ci);

    }

    public void cargarSaldo(Usuario usuario, float importe) {

    }

    @Override
    public double consultarSaldo(Usuario usuario) {
        return usuario.getSaldo();
    }

    @Override
    public double consultarSaldo(String usr) {
        Usuario usuario = usuariosRepo.getUsuarioByCI(usr);
        return consultarSaldo(usuario);
    }

    @Override
    @Transactional
    public boolean asociarTarjeta(String ci, Tarjeta tarjeta) {
        if(tarjeta == null){
            return false;
        }
        Usuario usuario = usuariosRepo.getUsuarioByCI(ci);
        if(usuario == null){
            return false;
        }
        
     
        tarjeta.setUsuarioAsociado(usuario);
        usuario.setTarjeta(tarjeta);
        
        if (usuariosRepo.asociarTarjeta(usuario, tarjeta)) {
            AsociarTarjeta at = new AsociarTarjeta();
            at.setTarjeta(tarjeta);
            at.setUsuario(usuario);
            asociarTarjetaEvent.fire(at);
            return true;
        }
        return false;

    }

    @Override
    public List<PasadaPorPeaje> consultarPasadas(String ciUsr, LocalDate fechaInicio, LocalDate fechaFin) {
        return usuariosRepo.getPasadasUsr(ciUsr, fechaInicio, fechaFin);
    }

    @Override
    public List<PasadaPorPeaje> consultarPasadas(String ciUsr, String tagVehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        return usuariosRepo.getPasadasVehiculo(tagVehiculo, fechaInicio, fechaFin);
    }

    @Override
    public List<Cuenta> obtenerCuentasPorTag(String tag) {
        Usuario usr = usuariosRepo.getUsuarioByTag(tag);
        List<Cuenta> cuentas = new ArrayList<>();
        if (usr != null) {
            CuentaPREPaga prepaga = new CuentaPREPaga();
            prepaga.setSaldo(usr.getSaldo());
            
            if(usr.getTarjeta() == null){
                CuentaPOSTPaga postpaga = new CuentaPOSTPaga();
                postpaga.setTarjeta(usr.getTarjeta());
                cuentas.add(postpaga);
            }
            

            cuentas.add(prepaga);
        }
        return cuentas;
    }

    @Override
    public List<Cuenta> obtenerCuentasPorCI(String ci) {
        Usuario usr = usuariosRepo.getUsuarioByCI(ci);
        List<Cuenta> cuentas = new ArrayList<>();
        if (usr != null) {
            CuentaPREPaga prepaga = new CuentaPREPaga();
            prepaga.setSaldo(usr.getSaldo());
            
            if(usr.getTarjeta() == null){
                CuentaPOSTPaga postpaga = new CuentaPOSTPaga();
                postpaga.setTarjeta(usr.getTarjeta());
                cuentas.add(postpaga);
            }
            

            cuentas.add(prepaga);
        }
        return cuentas;
    }

    @Override
    public boolean realizarPrePago(double importe, String tag) {
        Usuario usuario = usuariosRepo.getUsuarioByTag(tag);

        if (usuario.getSaldo() < importe) {
            System.out.println("saldo insuficiente" + usuario.getSaldo() + " " + importe);
            //saldoInsuficienteEvent.fire(new SaldoInsuficiente(importe, usuario.getCi(), cuentaCliente.getSaldo()));
            return false;
        }else{
            usuario.setSaldo(usuario.getSaldo() - importe);
            usuariosRepo.updateSaldo(usuario);
            evento.publicarPagoCuentaPrePaga(tag);
            return true;
        }
    }

    @Override
    public boolean realizarPostPago(double importe, String tag) {
        Usuario usuario = usuariosRepo.getUsuarioByTag(tag);
        Vehiculo vehiculo = usuariosRepo.getVehiculoByTag(tag);
        if(usuario == null){
            System.out.println("usuario no encontrado");
            return false;
        }
       
            if (usuario.getTarjeta() != null) {
                boolean resultado = pagos.notificarPago(usuario.getCi(), vehiculo.getTag(), importe, usuario.getTarjeta().getNro());
                if(resultado){
                    evento.publicarPagoCuentaPostPaga(tag);
                } 
                return resultado;
            } else {
                return false;
            }

    }

    @Override
    public Usuario getUsuario(String ci) {
        return usuariosRepo.getUsuarioByCI(ci);
    }

    @Override
    public boolean cargarSaldo(String ci, double importe) {
        Usuario usuario = usuariosRepo.getUsuarioByCI(ci);
        System.out.println(" cargar saldo " + usuario.getCi() + " " + usuario.getSaldo() + " " + importe );
        usuario.setSaldo(usuario.getSaldo() + importe);
        usuariosRepo.updateSaldo(usuario);
        return true;
    }
    @Override
    public Vehiculo getVehiculoByTag(String tag) {
        return usuariosRepo.getVehiculoByTag(tag);
    }
    @Override
    public void addPasada(PasadaPorPeaje pasada){
        usuariosRepo.addPasada(pasada);
    }

}
