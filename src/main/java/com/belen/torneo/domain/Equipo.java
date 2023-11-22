package com.belen.torneo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "bt_equipo_tb")
public class Equipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1172858854103070869L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("equipos")
	private Campeonato idCampeonato;
	
	@OneToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("equipos")
	private Usuario delegado;
	
	@OneToOne(optional = false)
    @JsonIgnoreProperties("equipos")
	private Grupo idGrupo;
	
	@Column(name = "puntos")
	private Integer puntos;
	
	@Column(name = "estado")
	private String estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Campeonato getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(Campeonato idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public Usuario getDelegado() {
		return delegado;
	}

	public void setDelegado(Usuario delegado) {
		this.delegado = delegado;
	}

	public Grupo getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Grupo idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
