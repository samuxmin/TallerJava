package org.tallerjava.moduloGestionCliente.dominio.clases;

import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity(name = "PasadaPorPeajeGestion")
@Getter
@Setter
public class PasadaPorPeaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate fecha;
    private double costo;
    private DataTipoCobroGestion tipoCobro;
    @ManyToOne
    @JsonbTransient
    Vehiculo vehiculo;
    String tagVehiculo;
    String matriculaVehiculo;
    public PasadaPorPeaje(int costo, LocalDate fecha, DataTipoCobroGestion tipoCobro, Vehiculo vehiculo) {
        this.costo = costo;
        this.fecha = fecha;
        this.tipoCobro = tipoCobro;
        this.vehiculo = vehiculo;
    }

    public PasadaPorPeaje() {
    }
   

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.tagVehiculo = vehiculo.getTag();
        this.matriculaVehiculo = vehiculo.getMatricula();
    }
    
}
