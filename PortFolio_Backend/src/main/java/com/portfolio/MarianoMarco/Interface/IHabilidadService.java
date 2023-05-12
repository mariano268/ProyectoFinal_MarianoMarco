package com.portfolio.MarianoMarco.Interface;

import com.portfolio.MarianoMarco.Entity.Habilidad;
import java.util.List;
import java.util.Optional;


public interface IHabilidadService {
    public List<Habilidad> list();
    
    public void save(Habilidad experiencia);
    
    public void delete(int id);
    
    public Optional<Habilidad> getOne(int id);
    
    public boolean existsById(int id);
    
    public boolean existsByNombre(String nombre);
    
    public Optional<Habilidad> getByNombre(String nombre);
}
