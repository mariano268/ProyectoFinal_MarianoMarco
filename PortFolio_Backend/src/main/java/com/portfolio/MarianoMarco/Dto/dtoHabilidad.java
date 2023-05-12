package com.portfolio.MarianoMarco.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class dtoHabilidad {
    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;
    
    public dtoHabilidad() {
    }

    public dtoHabilidad(String nombre , int porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }
}
