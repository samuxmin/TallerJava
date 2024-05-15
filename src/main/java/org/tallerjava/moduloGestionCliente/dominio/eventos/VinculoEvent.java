package org.tallerjava.moduloGestionCliente.dominio.eventos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class VinculoEvent {
    String ciUSr;
    String tag;
    String matricula;
    String tipo;
}
