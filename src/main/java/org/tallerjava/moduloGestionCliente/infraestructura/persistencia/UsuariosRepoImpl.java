package org.tallerjava.moduloGestionCliente.infraestructura.persistencia;

import jakarta.enterprise.context.ApplicationScoped;
import org.tallerjava.moduloGestionCliente.dominio.clases.Tag;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vinculo;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuariosRepoImpl implements UsuariosRepo{
    List<Usuario> usuarios = new ArrayList<>();
    public UsuariosRepoImpl() {

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
    public Vehiculo getVehiculoByTag(String tag){
        return getVehiculoByTag(new Tag(tag));
    }
    public Vehiculo getVehiculoByTag(Tag tag){
        Usuario usr = getUsuarioByTag(tag);
        for(Vinculo v : usr.getVinculosVehiculos()){
            Vehiculo vehiculo = v.getVehiculo();
            if(vehiculo.getTag().equals(tag)){
                return vehiculo;
            }
        }
        return null;
    }
}
