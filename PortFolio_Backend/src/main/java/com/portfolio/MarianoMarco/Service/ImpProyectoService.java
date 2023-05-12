package com.portfolio.MarianoMarco.Service;

import com.portfolio.MarianoMarco.Entity.Proyecto;
import com.portfolio.MarianoMarco.Interface.IProyectoService;
import com.portfolio.MarianoMarco.Repository.IProyectoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpProyectoService implements IProyectoService{
    @Autowired IProyectoRepository iproyectoRepository;
    
    @Override
    public List<Proyecto> list() {
        return iproyectoRepository.findAll();
    }

    @Override
    public void save(Proyecto experiencia) {
        iproyectoRepository.save(experiencia);
    }

    @Override
    public void delete(int id) {
        iproyectoRepository.deleteById(id);
    }

    @Override
    public Optional<Proyecto> getOne(int id) {
        return iproyectoRepository.findById(id);
    }

    @Override
    public boolean existsById(int id) {
        return iproyectoRepository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return iproyectoRepository.existsByNombre(nombre);
    }

    @Override
    public Optional<Proyecto> getByNombre(String nombre) {
        return iproyectoRepository.findByNombre(nombre);
    }
}
