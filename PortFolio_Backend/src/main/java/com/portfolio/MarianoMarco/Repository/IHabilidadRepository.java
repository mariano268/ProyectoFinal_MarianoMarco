package com.portfolio.MarianoMarco.Repository;

import com.portfolio.MarianoMarco.Entity.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHabilidadRepository extends JpaRepository<Habilidad,Integer>{
    public Optional<Habilidad> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
