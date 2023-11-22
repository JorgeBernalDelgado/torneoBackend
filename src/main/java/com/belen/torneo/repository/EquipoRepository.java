package com.belen.torneo.repository;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{

    List<Equipo> findByIdCampeonato(Campeonato campeonato);

    List<Equipo> findByNombre(String nombre);

    List<Equipo> findByDelegadoAndIdCampeonato(Usuario delegado, Campeonato campeonato);

    @Query(value =
            "select * from \n" +
                    "bt_equipo_tb bet\n" +
                    "where bet.id_campeonato_id = :torneo and bet.id_grupo_id = :grupo\n" +
                    "order by bet.puntos desc ",
            nativeQuery = true)
    List<Equipo> getPosicionEquipos(@Param("torneo") Integer torneo, @Param("grupo") Integer grupo);

}
