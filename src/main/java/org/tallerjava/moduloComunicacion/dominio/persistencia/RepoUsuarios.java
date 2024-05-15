package org.tallerjava.moduloComunicacion.dominio.persistencia;

import org.tallerjava.moduloComunicacion.dominio.Usuario;

import java.util.List;

public interface RepoUsuarios {
    public void addUsuario(Usuario usuario);
    public Usuario getUsuario(String ci);
    public List<Usuario> getUsuarios();
}
