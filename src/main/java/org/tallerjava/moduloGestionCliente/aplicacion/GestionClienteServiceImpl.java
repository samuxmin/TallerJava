package org.tallerjava.moduloGestionCliente.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import org.tallerjava.moduloGestionCliente.dominio.clases.*;
import org.tallerjava.moduloGestionCliente.dominio.eventos.*;
import org.tallerjava.moduloGestionCliente.infraestructura.persistencia.UsuariosRepo;
import org.tallerjava.moduloGestionCliente.interfase.GestionClienteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Default
@ApplicationScoped
public class GestionClienteServiceImpl implements GestionClienteService {
    private UsuariosRepo usuariosRepo = new UsuariosRepo();

    int idCuentas = 0;


    @Inject
    private Event<SaldoInsuficiente> saldoInsuficienteEvent;
    @Inject
    private Event<PagoCliente> pagoClienteEvent;
    @Inject
    private Event<Vinculo> vinculoEvent;
    @Inject
    private Event<DesvincularVehiculo> desvincularVehiculoEvent;
    @Inject
    private Event<CargaSaldo> cargaSaldoEvent;
    @Inject
    private Event<AsociarTarjeta> asociarTarjetaEvent;
    @Inject
    private Event<Usuario> crearUsuarioEvent;

    @Override
    public void altaClienteTelepeaje(Usuario usuario){
        ClienteTelepeaje nuevoClienteTelepeaje = new ClienteTelepeaje();
        nuevoClienteTelepeaje.setCuentaPrePaga(new CuentaPREPaga());
        nuevoClienteTelepeaje.setCuentaPostPaga(new CuentaPOSTPaga());
        usuario.setClienteTelepeaje(nuevoClienteTelepeaje);
        usuariosRepo.addUsuario(usuario);
        crearUsuarioEvent.fire(usuario);

    }
    @Override
    public void vincularVehiculo(Vehiculo vehiculo, Usuario usuario){
        Vinculo nuevoVinculo = new Vinculo(true,LocalDate.now(),vehiculo,usuario);
        usuario.getVinculosVehiculos().add(nuevoVinculo);
        vinculoEvent.fire(nuevoVinculo);

        //persistir??
    }
    @Override
    public void desvincularVehiculo(Usuario usr, Vehiculo v){
        Vinculo vinculoBorrar = null;
        List<Vinculo> vinculosCliente = usr.getVinculosVehiculos();
        for(Vinculo vinculo : vinculosCliente){
            if(vinculo.getVehiculo().equals(v)){
                vinculoBorrar = vinculo;
            }
        }
        if(vinculoBorrar != null){
            vinculosCliente.remove(vinculoBorrar);
            DesvincularVehiculo dv = new DesvincularVehiculo(v,usr);
            desvincularVehiculoEvent.fire(dv);
        }
    }
    @Override
    public List<Vehiculo> mostraVehiculosVinculados(Usuario usuario){
        List<Vehiculo> vehiculos= new ArrayList<>();
        List<Vinculo> vinculosCliente = usuario.getVinculosVehiculos();
        for(Vinculo vinculo : vinculosCliente){
            vehiculos.add(vinculo.getVehiculo());
        }
        return vehiculos;
    }
    @Override
    public void cargarSaldo(Usuario usuario,float importe) {
        if(usuario.getClienteTelepeaje() == null){
            throw new RuntimeException("Cliente telepeaje no encontrado");
        }
        CuentaPREPaga cuentaCliente = usuario.getClienteTelepeaje().getCuentaPrePaga();
        if(cuentaCliente == null){
            throw new RuntimeException("Cuenta no existente");
        }
        cuentaCliente.setSaldo(cuentaCliente.getSaldo() + importe);
        CargaSaldo carga = new CargaSaldo();
        carga.setSaldoCargado(importe);
        carga.setUsr(usuario);
        cargaSaldoEvent.fire(carga);
    }
    @Override
    public float consultarSaldo(Usuario usuario){
        if(usuario.getClienteTelepeaje() == null){
            throw new RuntimeException("Cliente telepeaje no encontrado");
        }
        CuentaPREPaga cuentaCliente = usuario.getClienteTelepeaje().getCuentaPrePaga();
        if(cuentaCliente == null){
            throw new RuntimeException("Cuenta no existente");
        }
        return cuentaCliente.getSaldo();
    }
    @Override
    public void asociarTarjeta(Usuario usuario, Tarjeta tarjeta){
        if(usuario.getClienteTelepeaje() == null){
            throw new RuntimeException("Cliente telepeaje no encontrado");
        }
        CuentaPOSTPaga cuentaCliente = usuario.getClienteTelepeaje().getCuentaPostPaga();
        if(cuentaCliente == null){
            CuentaPOSTPaga nuevaCuenta = new CuentaPOSTPaga(LocalDate.now(), idCuentas,tarjeta);

            usuario.getClienteTelepeaje().setCuentaPostPaga(nuevaCuenta);

            idCuentas++;
        }
            cuentaCliente.setTarjeta(tarjeta);
            AsociarTarjeta at = new AsociarTarjeta();
            at.setTarjeta(tarjeta);
            at.setUsuario(usuario);
            asociarTarjetaEvent.fire(at);
    }
    @Override
    public List<PasadaPorPeaje> consultarPasadas(Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin){
        List<PasadaPorPeaje> pasadas = new ArrayList<>();
        List<Vehiculo> vehiculos = mostraVehiculosVinculados(usuario);
        for(Vehiculo vehiculo : vehiculos){
            List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();
            for(PasadaPorPeaje pasada :pasadasVehiculo){
                if(pasada.getFecha().isAfter(fechaInicio) && pasada.getFecha().isBefore(fechaFin)  ){
                    pasadas.add(pasada);
                }
            }
        }
        return pasadas;
    }
    @Override
    public List<PasadaPorPeaje> consultarPasadas(Usuario usuario, Vehiculo vehiculo , LocalDate fechaInicio, LocalDate fechaFin){
        List<PasadaPorPeaje> pasadas = new ArrayList<>();
        if( !(mostraVehiculosVinculados(usuario).contains(vehiculo)) ){
            throw new RuntimeException("Vehiculo no encontrado para ese usuario");
        }
        List<PasadaPorPeaje> pasadasVehiculo = vehiculo.getPasadasPorPeaje();


        for(PasadaPorPeaje pasada :pasadasVehiculo){
            if(pasada.getFecha().isAfter(fechaInicio) && pasada.getFecha().isBefore(fechaFin)  ){
                pasadas.add(pasada);
            }
        }
        return pasadas;
    }
    @Override
    public List<Cuenta> obtenerCuentasPorTag(Tag tag){
        Usuario usr = usuariosRepo.getUsuarioByTag(tag);
        List<Cuenta> cuentas = new ArrayList<>();
        if(usr != null){
            cuentas.add(usr.getClienteTelepeaje().getCuentaPrePaga());

            cuentas.add(usr.getClienteTelepeaje().getCuentaPostPaga());
        }
        return null;
    }
    @Override
    public boolean realizarPrePago(float importe, Usuario usuario){
        if(usuario.getClienteTelepeaje() == null){
            return false;
            //throw new RuntimeException("Cliente telepeaje no encontrado");
        }
        CuentaPREPaga cuentaCliente = usuario.getClienteTelepeaje().getCuentaPrePaga();
        if(cuentaCliente == null){
            return false;
            //throw new RuntimeException("Cuenta no existente");
        }
        if(cuentaCliente.getSaldo() < importe){
            return false;
        }
        cuentaCliente.setSaldo(cuentaCliente.getSaldo() - importe);
        PagoCliente pago =new PagoCliente(TipoPago.PREPAGO,importe,usuario);
        pagoClienteEvent.fire(pago);
        return true;
    }
    @Override
    public boolean realizarPostPago(float importe, Usuario usuario, Vehiculo vehiculo){
        if(usuario.getClienteTelepeaje() == null){
            return false;
            //throw new RuntimeException("Cliente telepeaje no encontrado");
        }
        CuentaPOSTPaga cuentaCliente = usuario.getClienteTelepeaje().getCuentaPostPaga();
        if(cuentaCliente == null){
            return false;

            //throw new RuntimeException("Cuenta no existente");
        }else{
            if(cuentaCliente.getTarjeta( ) != null){
                PagoCliente pago =new PagoCliente(TipoPago.POSTPAGO,importe,usuario);
                pagoClienteEvent.fire(pago);
                //TODO HACER ALGO CON LA TARJETA???
                return true;
            }else{
                return false;
                //throw new RuntimeException("Tarjeta no existente");
            }

        }
    }

}
