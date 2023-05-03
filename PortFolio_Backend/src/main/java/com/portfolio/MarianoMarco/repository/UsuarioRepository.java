package com.portfolio.MarianoMarco.repository;

import com.portfolio.MarianoMarco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    
}
