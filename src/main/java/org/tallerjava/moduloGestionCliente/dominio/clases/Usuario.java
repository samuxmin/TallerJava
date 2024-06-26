package org.tallerjava.moduloGestionCliente.dominio.clases;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;
@Data
@Entity(name = "UsuarioGestion")
public class Usuario {
    @Id
    private String ci;
    private String nombre;
    private String email;
    private String password;
    private double saldo = 0;
    @OneToOne
    private Tarjeta tarjeta;
    //@OneToMany(mappedBy = "usuario")
    @Transient
    private List<Vinculo> vinculosVehiculos = new ArrayList<>();


    public Usuario(String email, String nombre, String ci, List<Vinculo> vinculosVehiculos) {
        this.email = email;
        this.nombre = nombre;
        this.ci = ci;
        this.vinculosVehiculos = vinculosVehiculos;
        this.saldo = 0;
    }
    public Usuario() {
        this.saldo = 0;
    }

}
