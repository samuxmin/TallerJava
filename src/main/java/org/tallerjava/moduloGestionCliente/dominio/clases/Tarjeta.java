package org.tallerjava.moduloGestionCliente.dominio.clases;

import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity(name="TarjetaGestion")
public class Tarjeta {
    @Id
    private long nro;
    private String nombre;
    private LocalDate fechaVto;
    @OneToOne
    @JsonbTransient
    private Usuario usuarioAsociado;

    public Tarjeta(LocalDate fechaVto, String nombre, int nro) {
        this.fechaVto = fechaVto;
        this.nombre = nombre;
        this.nro = nro;
    }

    public Tarjeta() {
    }

    public LocalDate getFechaVto() {
        return fechaVto;
    }

    public void setFechaVto(LocalDate fechaVto) {
        this.fechaVto = fechaVto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNro() {
        return nro;
    }

    public void setNro(long nro) {
        this.nro = nro;
    }
    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }
    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }
}
