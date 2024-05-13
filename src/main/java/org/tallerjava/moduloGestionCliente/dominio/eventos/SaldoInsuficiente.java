package org.tallerjava.moduloGestionCliente.dominio.eventos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tallerjava.moduloMonitoreo.dominio.clases.CuentaPREPaga;
import org.tallerjava.moduloMonitoreo.dominio.clases.Usuario;
import org.tallerjava.moduloMonitoreo.dominio.clases.Vehiculo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaldoInsuficiente {
    double saldoCobro;
    Usuario usuario;
    Vehiculo vehiculo;
    CuentaPREPaga cuentaUsr;
}
