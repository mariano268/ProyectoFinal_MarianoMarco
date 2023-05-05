package com.portfolio.MarianoMarco.Interface;

import com.portfolio.MarianoMarco.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //Traer una lista de personas
    public List<Persona> getPersona();
    
    //Guardar una persona
    public void savePersona(Persona persona);
    
    //EliminarPersona
    public void deletePersona(Long id);
    
    //Buscar una persona
    public Persona findPersona(Long id);
}

