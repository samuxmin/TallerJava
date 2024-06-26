package org.tallerjava.moduloGestionCliente.interfase.evento.out;

import org.tallerjava.moduloGestionCliente.dominio.clases.Tarjeta;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsociarTarjeta {
    Tarjeta tarjeta;
    Usuario usuario;
}
