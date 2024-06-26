package org.tallerjava.moduloSucive.interfase.eventos;

import lombok.Data;
import lombok.Getter;

@Getter
@Data

public class PagoSucive {

    String matricula;
    double importe;
    public PagoSucive(String matricula, double importe) {}
}
