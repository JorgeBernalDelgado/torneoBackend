package com.belen.torneo.repository;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{

    List<Grupo> findByIdCampeonato(Campeonato campeonatoSave);
}
