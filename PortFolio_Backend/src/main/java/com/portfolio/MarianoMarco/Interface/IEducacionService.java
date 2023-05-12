package com.portfolio.MarianoMarco.Interface;

import com.portfolio.MarianoMarco.Entity.Educacion;
import java.util.List;
import java.util.Optional;


public interface IEducacionService {
    public List<Educacion> list();
    
    public void save(Educacion experiencia);
    
    public void delete(int id);
    
    public Optional<Educacion> getOne(int id);
    
    public boolean existsById(int id);
    
    public boolean existsByNombre(String nombre);
    
    public Optional<Educacion> getByNombre(String nombre);
}
