package org.tallerjava.moduloMediosDePago.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name="VehiculoPago")
public class Vehiculo {
    @Id
    private String tag;

/*     @Transient
    @OneToMany
    private List<PasadaPorPeaje> pasadasPorPeaje; */
    
    @OneToOne
    private Vinculo vinculo;
   
    public Vehiculo(Vinculo v) {
        this.vinculo = v;
    }

    public Vehiculo() {
    }


    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
