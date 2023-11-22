package com.belen.torneo.repository;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.DetallePartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePartidoRepository extends JpaRepository<DetallePartido, Integer> {

    List<DetallePartido> findByCampeonato(Campeonato campeonato);
}
