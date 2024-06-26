package org.tallerjava.moduloPeaje.dominio;

import java.time.LocalDate;
//@Entity(name = "vinculoPeaje")
public class Vinculo {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    private UsuarioPeaje usuario;

    private LocalDate fechaIni;
    private boolean activo;

    //@ManyToOne
    //@JoinColumn
    private Vehiculo vehiculo;

    public Vinculo() {}

    public Vinculo(boolean activo, LocalDate fechaIni, Vehiculo vehiculo,UsuarioPeaje usuario) {
        this.usuario = usuario;
        this.activo = activo;
        this.fechaIni = fechaIni;
        this.vehiculo = vehiculo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    public UsuarioPeaje getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioPeaje usuario) {
        this.usuario = usuario;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
}
