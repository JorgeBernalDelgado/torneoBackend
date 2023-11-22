package com.belen.torneo.domain;

import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "bt_campeonato_tb")
public class Campeonato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7800353910771498913L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre_campeonato")
	private String nombreCampeonato;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "deporte")
	private Integer deporte;
	
	@Column(name = "categoria")
	private Integer categoria;
	
	@Column(name = "rama")
	private String rama;
	
	@Column(name = "planilla")
	private Integer planilla;
	
	@Column(name = "localidad")
	private Integer localidad;
	
	@Column(name = "rango_annio")
	private Integer rangoAnnio;
	
	@Column(name = "division")
	private Integer division;

	@Column(name = "carnet")
	private String carnet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreCampeonato() {
		return nombreCampeonato;
	}

	public void setNombreCampeonato(String nombreCampeonato) {
		this.nombreCampeonato = nombreCampeonato;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getDeporte() {
		return deporte;
	}

	public void setDeporte(Integer deporte) {
		this.deporte = deporte;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	public Integer getPlanilla() {
		return planilla;
	}

	public void setPlanilla(Integer planilla) {
		this.planilla = planilla;
	}

	public Integer getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Integer localidad) {
		this.localidad = localidad;
	}

	public Integer getRangoAnnio() {
		return rangoAnnio;
	}

	public void setRangoAnnio(Integer rangoAnnio) {
		this.rangoAnnio = rangoAnnio;
	}

	public Integer getDivision() {
		return division;
	}

	public void setDivision(Integer division) {
		this.division = division;
	}

	public String getCarnet() { return carnet; }

	public void setCarnet(String carnet) { this.carnet = carnet; }
}
