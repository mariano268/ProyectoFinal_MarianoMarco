package com.portfolio.MarianoMarco.Interface;

import com.portfolio.MarianoMarco.Entity.Proyecto;
import java.util.List;
import java.util.Optional;


public interface IProyectoService {
    public List<Proyecto> list();
    
    public void save(Proyecto experiencia);
    
    public void delete(int id);
    
    public Optional<Proyecto> getOne(int id);
    
    public boolean existsById(int id);
    
    public boolean existsByNombre(String nombre);
    
    public Optional<Proyecto> getByNombre(String nombre);
}
