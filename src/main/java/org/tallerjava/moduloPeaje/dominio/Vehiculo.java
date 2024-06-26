package org.tallerjava.moduloPeaje.dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity(name = "vehiculoPeaje")
public class Vehiculo {
    @Id
    private String tag;
    @Transient
    @OneToMany
    private List<PasadaPorPeajePeaje> pasadasPorPeaje;

    private Nacionalidad nacionalidad;
    private String matricula;

    public Vehiculo(List<PasadaPorPeajePeaje> pasadasPorPeaje, Vinculo v,String tag,String matricula ,Nacionalidad nacionalidad) {
        this.pasadasPorPeaje = pasadasPorPeaje;
        this.tag = tag;
        this.nacionalidad = nacionalidad;
        this.matricula = matricula;
    }

    public String getMatricula(){
        return matricula;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public Vehiculo() {
        this.pasadasPorPeaje = new ArrayList<>();
    }

    public String getTag(){
        return tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public List<PasadaPorPeajePeaje> getPasadasPorPeaje() {
        return pasadasPorPeaje;
    }

    public void setPasadasPorPeaje(List<PasadaPorPeajePeaje> pasadasPorPeaje) {
        this.pasadasPorPeaje = pasadasPorPeaje;
    }

}