package org.tallerjava.moduloGestionCliente.dominio.clases;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;

@Data

@Entity(name = "VehiculoGestion")
public class Vehiculo {
    @Id
    @Getter
    private String tag;
    @OneToMany
    @Transient
    private List<PasadaPorPeaje> pasadasPorPeaje;
    @OneToOne
    @JsonbTransient
    private Vinculo vinculo;
    private Nacionalidad nacionalidad;
    @Getter
    private String matricula;
    public Vehiculo(List<PasadaPorPeaje> pasadasPorPeaje, Vinculo v,String tag,String matricula ,Nacionalidad nacionalidad) {
        this.pasadasPorPeaje = pasadasPorPeaje;
        this.vinculo = v;
        this.tag = tag;
        this.nacionalidad = nacionalidad;
        this.matricula = matricula;
    }


    public Vehiculo() {
        this.pasadasPorPeaje = new ArrayList<>();
    }

}
