package com.belen.torneo.repository;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Deportista;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.EquipoDeportista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoDeportistaRepository extends JpaRepository<EquipoDeportista, Integer> {

    List<EquipoDeportista> findByIdCampeonatoAndIdDeportista(Campeonato campeonato, Deportista deportista);

    List<EquipoDeportista> findByIdEquipoAndIdCampeonato(Equipo equipo, Campeonato campeonato);

    Long deleteByIdDeportista(Deportista deportista);

    @Query(value =
            "select * from \n" +
                    "bt_equipo_deportista_tb bedt \n" +
                    "join bt_deportista_tb bdt \n" +
                    "on bedt.id_deportista_id = bdt.id \n" +
                    "where bedt.id_campeonato_id = :torneo\n" +
                    "order by bdt.anotaciones desc \n" +
                    "limit 10",
            nativeQuery = true)
    List<EquipoDeportista> getDeportistaAnotaciones(@Param("torneo") Integer torneo);

    @Query(value =
            "select * from \n" +
                    "bt_equipo_deportista_tb bedt \n" +
                    "join bt_deportista_tb bdt \n" +
                    "on bedt.id_deportista_id = bdt.id \n" +
                    "where bedt.id_campeonato_id = :torneo\n" +
                    "and bdt.posicion like ('portero')\n" +
                    "order by bdt.goles_recibidos asc\n" +
                    "limit 10",
            nativeQuery = true)
    List<EquipoDeportista> getVallaMenosVencida(@Param("torneo") Integer torneo);

}
