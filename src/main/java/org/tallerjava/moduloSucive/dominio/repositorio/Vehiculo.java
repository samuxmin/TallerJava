package org.tallerjava.moduloSucive.dominio.repositorio;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculoSucive")
public class Vehiculo {

    private String tag;
    @Id
    private String matricula;
    @Transient
    @OneToMany
    private List<PasadaPorPeaje> pasadasPorPeaje;


    public Vehiculo() {
    }

    public List<PasadaPorPeaje> getPasadasPorPeaje() {
        return pasadasPorPeaje;
    }

    public void setPasadasPorPeaje(List<PasadaPorPeaje> pasadasPorPeaje) {
        this.pasadasPorPeaje = pasadasPorPeaje;
    }

}
