package org.tallerjava.modulosucive.dominio.repositorio;
import java.util.List;


public class UsuarioNacional extends Usuario {
    ClienteSucive clienteSucive;
    public UsuarioNacional() {
    }

    public UsuarioNacional(String email, String nombre, String ci, List<Vinculo> vinculosVehiculos, ClienteTelepeaje clienteTelepeaje, ClienteSucive clS) {
        super(email, nombre, ci, vinculosVehiculos, clienteTelepeaje);
        this.clienteSucive = clS;
    }
}
