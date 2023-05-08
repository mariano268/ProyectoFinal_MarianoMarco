package com.portfolio.MarianoMarco.Interface;

import com.portfolio.MarianoMarco.Entity.Experiencia;
import java.util.List;
import java.util.Optional;


public interface IExperienciaService {
    
    public List<Experiencia> list();
    
    public void save(Experiencia experiencia);
    
    public void delete(int id);
    
    public Optional<Experiencia> getOne(int id);
    
    public boolean existsById(int id);
    
    public boolean existsByNombre(String nombre);
    
    public Optional<Experiencia> getByNombre(String nombre);
}
