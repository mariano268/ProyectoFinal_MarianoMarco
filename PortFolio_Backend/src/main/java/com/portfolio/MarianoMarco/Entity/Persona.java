package com.portfolio.MarianoMarco.Entity;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 1, max= 25, message="No cumple con longitud")
    private String nombre;
    @NotNull
    @Size(min = 1, max= 25, message="No cumple con longitud")
    private String apellido;
    
    @NotNull
    private String descripcion;
    
    private String imagen;
    
    public Persona() {
    }

    public Persona(Long id, String nombre, String apellido, String descripcion, String imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
    
}
