package com.portfolio.MarianoMarco.security.repository;

import com.portfolio.MarianoMarco.security.entity.Rol;
import com.portfolio.MarianoMarco.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
