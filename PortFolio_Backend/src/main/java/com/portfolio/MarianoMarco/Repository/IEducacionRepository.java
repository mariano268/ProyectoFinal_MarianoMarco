package com.portfolio.MarianoMarco.Repository;

import com.portfolio.MarianoMarco.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacionRepository extends JpaRepository<Educacion,Integer>{
    public Optional<Educacion> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
