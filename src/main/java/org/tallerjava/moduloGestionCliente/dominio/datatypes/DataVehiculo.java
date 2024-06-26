package org.tallerjava.moduloGestionCliente.dominio.datatypes;

import lombok.Data;
import org.tallerjava.moduloGestionCliente.dominio.clases.Nacionalidad;
import org.tallerjava.moduloGestionCliente.dominio.clases.Vehiculo;

@Data
public class DataVehiculo {
    boolean extranjero;
    String tag;
    String matricula;
    public DataVehiculo(){

    }
    public DataVehiculo(Vehiculo vehiculo) {
            tag = vehiculo.getTag();
        if(vehiculo.getNacionalidad().equals(Nacionalidad.NACIONAL)){
            extranjero = false;
            matricula = vehiculo.getMatricula();
        }else{
            extranjero = true;
        }
    }
}
