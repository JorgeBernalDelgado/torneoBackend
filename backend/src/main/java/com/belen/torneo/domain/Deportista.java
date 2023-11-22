package com.belen.torneo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "bt_deportista_tb")
public class Deportista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1603854409866593403L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "identificacion")
	private String identificacion;
	
	@Column(name = "tipo_identificacion")
	private Integer tipoIdentificacion;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;

	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
    
	@Column(name = "foto_deportista")
	private String fotoDeportista;
    
	@Column(name = "numero_camiseta")
	private Integer numeroCamiseta;

	@Column(name = "posicion")
	private String posicion;
    
	@Column(name = "goles_recibidos")
	private Integer golesRecibidos;
    
	@Column(name = "anotaciones")
	private Integer anotaciones;

	@Column(name = "documento")
	private String documento;

	@Column(name = "cestas")
	private Integer cestas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Integer getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(Integer tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFotoDeportista() {
		return fotoDeportista;
	}

	public void setFotoDeportista(String fotoDeportista) {
		this.fotoDeportista = fotoDeportista;
	}

	public Integer getNumeroCamiseta() {
		return numeroCamiseta;
	}

	public void setNumeroCamiseta(Integer numeroCamiseta) {
		this.numeroCamiseta = numeroCamiseta;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Integer getGolesRecibidos() {
		return golesRecibidos;
	}

	public void setGolesRecibidos(Integer golesRecibidos) {
		this.golesRecibidos = golesRecibidos;
	}

	public Integer getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(Integer anotaciones) {
		this.anotaciones = anotaciones;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Integer getCestas() {
		return cestas;
	}

	public void setCestas(Integer cestas) {
		this.cestas = cestas;
	}
}
