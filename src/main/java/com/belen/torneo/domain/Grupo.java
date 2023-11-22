package com.belen.torneo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "bt_grupo_tb")
public class Grupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4110118853833391437L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "codigo")
	private String codigo;
	
	@ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("grupos")
	private Campeonato idCampeonato;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Campeonato getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(Campeonato idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

}
