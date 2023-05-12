package com.portfolio.MarianoMarco.Service;

import com.portfolio.MarianoMarco.Entity.Habilidad;
import com.portfolio.MarianoMarco.Interface.IHabilidadService;
import com.portfolio.MarianoMarco.Repository.IHabilidadRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpHabilidadService implements IHabilidadService{
    @Autowired IHabilidadRepository ihabilidadRepository;
    
    @Override
    public List<Habilidad> list() {
        return ihabilidadRepository.findAll();
    }

    @Override
    public void save(Habilidad experiencia) {
        ihabilidadRepository.save(experiencia);
    }

    @Override
    public void delete(int id) {
        ihabilidadRepository.deleteById(id);
    }

    @Override
    public Optional<Habilidad> getOne(int id) {
        return ihabilidadRepository.findById(id);
    }

    @Override
    public boolean existsById(int id) {
        return ihabilidadRepository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return ihabilidadRepository.existsByNombre(nombre);
    }

    @Override
    public Optional<Habilidad> getByNombre(String nombre) {
        return ihabilidadRepository.findByNombre(nombre);
    }
}
