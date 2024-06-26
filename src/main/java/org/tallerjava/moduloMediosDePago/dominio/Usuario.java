package org.tallerjava.moduloMediosDePago.dominio;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity(name="UsuarioPago")
public class Usuario {
    @Id
    private String ci;
    private String nombre;
    private String email;
    @Transient
    private List<Vinculo> vinculosVehiculos = new ArrayList<>();
    @OneToOne
    private ClienteTelepeaje clienteTelepeaje;
    @Transient
    private List<Pago> pagos = new ArrayList<>();

    public Usuario(String email, String nombre, String ci,List<Vinculo> vinculosVehiculos,ClienteTelepeaje clienteTelepeaje) {
        this.email = email;
        this.nombre = nombre;
        this.ci = ci;
        this.vinculosVehiculos = vinculosVehiculos;
        this.clienteTelepeaje = clienteTelepeaje;
    }
    public Usuario() {
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

    public ClienteTelepeaje getClienteTelepeaje() {
        return clienteTelepeaje;
    }

    public void setClienteTelepeaje(ClienteTelepeaje clienteTelepeaje) {
        this.clienteTelepeaje = clienteTelepeaje;
    }

    public List<Vinculo> getVinculosVehiculos() {
        return vinculosVehiculos;
    }

    public void setVinculosVehiculos(List<Vinculo> vinculosVehiculos) {
        this.vinculosVehiculos = vinculosVehiculos;
    }

}
