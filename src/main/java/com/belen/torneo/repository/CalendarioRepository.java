package com.belen.torneo.repository;

import com.belen.torneo.domain.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Integer> {

    @Query(value =
            "SELECT *\n" +
                    "FROM (  SELECT  *, \n" +
                    "                COUNT(*) OVER(PARTITION BY fecha) N\n" +
                    "        FROM bt_calendario_tb where id_campeonato_id = :campeonato) as A\n" +
                    "WHERE N > 0\n" +
                    "GROUP BY hora,fecha,a.id,a.equipoa,a.equipob,a.categoria," +
                    "a.id_campeonato_id,a.fase,a.jornada,a.n\n" +
                    "ORDER BY fecha,hora ASC",
            nativeQuery = true)
    List<Calendario> listarDia(@Param("campeonato") Integer campeonato);
}
