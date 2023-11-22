package com.belen.torneo.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;	

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.sun.istack.NotNull;

@Entity
@Table(schema = "public", name = "bt_partido_tb")
public class Partido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8176174226247506685L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @ManyToOne
    @JoinColumn(name = "equipo_a")
    private Equipo equipoA;
    
    @ManyToOne
    @JoinColumn(name = "equipo_b")
    private Equipo equipoB;
	
	@Column(name = "fecha")
	private LocalDate fecha;
	
	@Column(name = "hora")
	private String hora;
	
	@Column(name = "cancha")
	private Integer cancha;
	
	@Column(name = "nivel")
	private String nivel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Equipo getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(Equipo equipoA) {
		this.equipoA = equipoA;
	}

	public Equipo getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(Equipo equipoB) {
		this.equipoB = equipoB;
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

	public Integer getCancha() {
		return cancha;
	}

	public void setCancha(Integer cancha) {
		this.cancha = cancha;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
