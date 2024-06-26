package org.tallerjava.moduloPeaje.dominio;

import java.util.ArrayList;
import java.util.List;

//@Entity(name = "UsuarioPeaje")
public class UsuarioPeaje {
    //@Id
    private String ci;
    private String nombre;
    private String email;

    //@OneToMany(mappedBy = "usuario")
    //@Transient
    private List<Vinculo> vinculosVehiculos = new ArrayList<>();


    public UsuarioPeaje(String email, String nombre, String ci, List<Vinculo> vinculosVehiculos) {
        this.email = email;
        this.nombre = nombre;
        this.ci = ci;
        this.vinculosVehiculos = vinculosVehiculos;
    }
    public UsuarioPeaje() {
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Vinculo> getVinculosVehiculos() {
        return vinculosVehiculos;
    }

    public void setVinculosVehiculos(List<Vinculo> vinculosVehiculos) {
        this.vinculosVehiculos = vinculosVehiculos;
    }
}
