package org.tallerjava.moduloComunicacion.dominio.persistencia;

import jakarta.enterprise.context.ApplicationScoped;
import org.tallerjava.moduloComunicacion.dominio.Usuario;

import java.util.List;
@ApplicationScoped
public class RepoUsuariosImpl implements RepoUsuarios {
    private List<Usuario> usuarios;

    public void addUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    public Usuario getUsuario(String ci) {
        for(Usuario usuario : usuarios){
            if(usuario.getCi().equals(ci)){
                return usuario;
            }
        }
        return null;
    }
    public List<Usuario> getUsuarios(){
        return usuarios;
    }
}
