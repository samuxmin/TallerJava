package org.tallerjava.moduloGestionCliente.infraestructura.persistencia;

import org.tallerjava.moduloGestionCliente.dominio.clases.Tag;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;

public interface UsuariosRepo {
    void addUsuario(Usuario u);
    Vehiculo getVehiculoByTag(String tag);
    Vehiculo getVehiculoByTag(Tag tag);
    Usuario getUsuarioByTag(Tag tag);
}
