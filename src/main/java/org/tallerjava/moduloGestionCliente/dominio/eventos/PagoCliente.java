package org.tallerjava.moduloGestionCliente.dominio.eventos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tallerjava.moduloGestionCliente.dominio.clases.TipoPago;
import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;

@Getter
@Setter
@NoArgsConstructor
public class PagoCliente {
    TipoPago tipoPago;
    double importe;
    Usuario usuario;

    public PagoCliente(TipoPago tipoPago, double importe, Usuario usuario) {}
}
