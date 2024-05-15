package org.tallerjava.moduloComunicacion.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.tallerjava.moduloComunicacion.dominio.Usuario;
import org.tallerjava.moduloComunicacion.dominio.persistencia.RepoUsuarios;
import org.tallerjava.moduloGestionCliente.dominio.eventos.SaldoInsuficiente;
import org.tallerjava.moduloMediosDePago.interfase.eventos.CobroRechazado;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ComunicacionServiceImpl implements ComunicacionService {
    @Inject
    private RepoUsuarios repo;


    public void altaCliente(@Observes org.tallerjava.moduloGestionCliente.dominio.clases.Usuario usrData){
        Usuario usuario = new Usuario();
        usuario.setNombre(usrData.getNombre());
        usuario.setMail(usrData.getEmail());
        usuario.setCi(usrData.getCi());

        repo.addUsuario(usuario);
    }
    public void notificarSaldoInsuficiente(@Observes SaldoInsuficiente si){
        notificarInformacion(si.getCi(),"saldo insuficiente");
    }
    public void notificarInformacion(String ci, String informacion){
        Usuario usr = repo.getUsuario(ci);
        usr.getNotificaciones().add(informacion);
    }
    public List<String> getNotificaciones(String ci){
        return repo.getUsuario(ci).getNotificaciones();
    }
    public void notificarTarjetaBloqueada(@Observes CobroRechazado cobroRechazado){
        notificarInformacion(cobroRechazado.getCiUsr(), "la tarjeta "+ cobroRechazado.getNroTarjeta() +" esta bloqueada");
    }
}
