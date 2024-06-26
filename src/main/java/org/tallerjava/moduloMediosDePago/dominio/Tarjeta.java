package org.tallerjava.moduloMediosDePago.dominio;
import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name="TarjetaPago")
public class Tarjeta {
    @Id
    private long nro;
    private String nombre;
    private LocalDate fechaVto;
    @OneToOne
    @JsonbTransient
    private Usuario usuarioAsociado;

    public Tarjeta(LocalDate fechaVto, String nombre, long nro) {
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

    public boolean esValida() {
        return LocalDate.now().isBefore(fechaVto);
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }
    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

}
