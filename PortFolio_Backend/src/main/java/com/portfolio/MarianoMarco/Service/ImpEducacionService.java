package com.portfolio.MarianoMarco.Service;

import com.portfolio.MarianoMarco.Entity.Educacion;
import com.portfolio.MarianoMarco.Interface.IEducacionService;
import com.portfolio.MarianoMarco.Repository.IEducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEducacionService implements IEducacionService{
    @Autowired IEducacionRepository ieducacionRepository;
    
    @Override
    public List<Educacion> list() {
        return ieducacionRepository.findAll();
    }

    @Override
    public void save(Educacion experiencia) {
        ieducacionRepository.save(experiencia);
    }

    @Override
    public void delete(int id) {
        ieducacionRepository.deleteById(id);
    }

    @Override
    public Optional<Educacion> getOne(int id) {
        return ieducacionRepository.findById(id);
    }

    @Override
    public boolean existsById(int id) {
        return ieducacionRepository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return ieducacionRepository.existsByNombre(nombre);
    }

    @Override
    public Optional<Educacion> getByNombre(String nombre) {
        return ieducacionRepository.findByNombre(nombre);
    }
}
