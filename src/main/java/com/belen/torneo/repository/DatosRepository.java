package com.belen.torneo.repository;


import com.belen.torneo.domain.Datos;
import com.belen.torneo.domain.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatosRepository extends JpaRepository<Datos, Integer> {

    List<Datos> findByCategoria(String categoria);
}
