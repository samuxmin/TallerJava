package org.tallerjava.moduloGestionCliente.dominio.eventos;

import lombok.*;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesvincularVehiculo {
    Vehiculo vehiculo;
    Usuario usuario;

}
