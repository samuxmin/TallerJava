package org.tallerjava.moduloGestionCliente.infraestructura.persistencia;

import org.tallerjava.moduloGestionCliente.dominio.clases.Tag;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vinculo;

import java.util.ArrayList;
import java.util.List;

public class UsuariosRepo {
    List<Usuario> usuarios = new ArrayList<>();
    public UsuariosRepo() {

    }
    public void addUsuario(Usuario u){
        usuarios.add(u);
    }
    public Usuario getUsuarioByTag(Tag tag){
        for (Usuario usuario : usuarios) {
            for(Vinculo vinc : usuario.getVinculosVehiculos()){
                Vehiculo vehiculo = vinc.getVehiculo();
                if(vehiculo.getTag().equals(tag)){
                    return usuario;
                }
            }
        }
        return null;
    }
}
