package com.belen.torneo.repository;

import com.belen.torneo.domain.Rol;
import com.belen.torneo.enums.ERol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(ERol nombre);
}
