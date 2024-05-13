package org.tallerjava.moduloMonitoreo.dominio.clases;

import java.util.List;

public class UsuarioExtranjero extends Usuario {
    public UsuarioExtranjero() {
    }

    public UsuarioExtranjero(String email, String nombre, String ci, List<Vinculo> vinculosVehiculos, ClienteTelepeaje clienteTelepeaje) {
        super(email, nombre, ci, vinculosVehiculos, clienteTelepeaje);
    }
}
