package org.tallerjava.moduloGestionCliente.dominio.eventos;

import lombok.*;
import org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsociarTarjeta {
    Tarjeta tarjeta;
    Usuario usuario;
}
