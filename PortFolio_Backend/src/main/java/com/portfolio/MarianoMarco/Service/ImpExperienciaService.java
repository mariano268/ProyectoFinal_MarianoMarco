package com.portfolio.MarianoMarco.Service;

import com.portfolio.MarianoMarco.Entity.Experiencia;
import com.portfolio.MarianoMarco.Interface.IExperienciaService;
import com.portfolio.MarianoMarco.Repository.IExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpExperienciaService implements IExperienciaService{
    @Autowired IExperienciaRepository iexperienciaRepository;
    
    @Override
    public List<Experiencia> list() {
        return iexperienciaRepository.findAll();
    }

    @Override
    public void save(Experiencia experiencia) {
        iexperienciaRepository.save(experiencia);
    }

    @Override
    public void delete(int id) {
        iexperienciaRepository.deleteById(id);
    }

    @Override
    public Optional<Experiencia> getOne(int id) {
        return iexperienciaRepository.findById(id);
    }

    @Override
    public boolean existsById(int id) {
        return iexperienciaRepository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return iexperienciaRepository.existsByNombre(nombre);
    }

    @Override
    public Optional<Experiencia> getByNombre(String nombre) {
        return iexperienciaRepository.findByNombre(nombre);
    }
}
