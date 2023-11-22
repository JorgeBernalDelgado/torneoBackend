package com.belen.torneo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "bt_detalle_partido_tb")
public class DetallePartido implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1603854409866593403L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "equipoa")
    private Equipo equipoa;

    @ManyToOne
    @JoinColumn(name = "equipob")
    private Equipo equipob;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "anotacionesa")
    private Integer anotacionesa;

    @Column(name = "anotacionesb")
    private Integer anotacionesb;

    @Column(name = "arbitro")
    private String arbitro;

    @Column(name = "juez1")
    private String juez1;

    @Column(name = "juez2")
    private String juez2;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "equipo_ganador")
    private String equipoGanador;

    @Column(name = "informe_arbitral")
    private String informeArbitral;

    @ManyToOne
    @JoinColumn(name = "campeonato")
    private Campeonato campeonato;

    @Column(name = "fase")
    private String fase;

    @Column(name = "jornada")
    private Integer jornada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipo getEquipoa() {
        return equipoa;
    }

    public void setEquipoa(Equipo equipoa) {
        this.equipoa = equipoa;
    }

    public Equipo getEquipob() {
        return equipob;
    }

    public void setEquipob(Equipo equipob) {
        this.equipob = equipob;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getAnotacionesa() {
        return anotacionesa;
    }

    public void setAnotacionesa(Integer anotacionesa) {
        this.anotacionesa = anotacionesa;
    }

    public Integer getAnotacionesb() {
        return anotacionesb;
    }

    public void setAnotacionesb(Integer anotacionesb) {
        this.anotacionesb = anotacionesb;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public String getJuez1() {
        return juez1;
    }

    public void setJuez1(String juez1) {
        this.juez1 = juez1;
    }

    public String getJuez2() {
        return juez2;
    }

    public void setJuez2(String juez2) {
        this.juez2 = juez2;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEquipoGanador() {
        return equipoGanador;
    }

    public void setEquipoGanador(String equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    public String getInformeArbitral() {
        return informeArbitral;
    }

    public void setInformeArbitral(String informeArbitral) {
        this.informeArbitral = informeArbitral;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Integer getJornada() {
        return jornada;
    }

    public void setJornada(Integer jornada) {
        this.jornada = jornada;
    }
}
