package com.belen.torneo.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "bt_equipo_deportista_tb")
public class EquipoDeportista implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1172858854103070869L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_equipo_id")
    private Equipo idEquipo;

    @ManyToOne
    @JoinColumn(name = "id_deportista_id")
    private Deportista idDeportista;

    @ManyToOne
    @JoinColumn(name = "id_campeonato_id")
    private Campeonato idCampeonato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Deportista getIdDeportista() {
        return idDeportista;
    }

    public void setIdDeportista(Deportista idDeportista) {
        this.idDeportista = idDeportista;
    }

    public Campeonato getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(Campeonato idCampeonato) {
        this.idCampeonato = idCampeonato;
    }
}
