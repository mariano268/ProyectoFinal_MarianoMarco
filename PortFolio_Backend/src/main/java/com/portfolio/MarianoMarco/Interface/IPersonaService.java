package com.portfolio.MarianoMarco.Interface;

import com.portfolio.MarianoMarco.Entity.Persona;
import java.util.List;
import java.util.Optional;


public interface IPersonaService {
    
    public List<Persona> list();
    
    public void save(Persona persona);
    
    public void delete(int id);
    
    public Optional<Persona> getOne(int id);
    
    public boolean existsById(int id);
    
    public boolean existsByNombre(String nombre);
    
    public Optional<Persona> getByNombre(String nombre);
}

