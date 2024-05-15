package org.tallerjava.moduloComunicacion.dominio;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {
    public Usuario(){}
    private String nombre;
    private String mail;
    private String ci;
    private List<String> notificaciones;
}
