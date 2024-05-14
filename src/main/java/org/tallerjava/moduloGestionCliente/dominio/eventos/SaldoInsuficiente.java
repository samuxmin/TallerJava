package org.tallerjava.moduloGestionCliente.dominio.eventos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaldoInsuficiente {
    double saldoCobro;
    String ci;
    double saldoUsr;
    public String toString(){
        return "Fallo el cobro para el usuario con ci: " + ci + ", saldo actual: " + saldoUsr + ", saldo a cobrar: " + saldoCobro;
    }
}
