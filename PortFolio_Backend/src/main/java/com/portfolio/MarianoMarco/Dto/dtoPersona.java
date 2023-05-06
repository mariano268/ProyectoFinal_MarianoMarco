package com.portfolio.MarianoMarco.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class dtoPersona {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String imagen;

    public dtoPersona() {
    }

    public dtoPersona(String nombre, String apellido, String descripcion, String imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
}
