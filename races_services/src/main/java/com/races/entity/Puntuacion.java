package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidad de la tabla Puntuacion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Entity
public class Puntuacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sesion")
	private Sesion sesion;

	private Integer posicion;

	private Integer puntos;

	/**
	 * Constructor por defecto
	 */
	public Puntuacion() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param reglamento
	 * @param posicion
	 * @param puntos
	 * @param tipoSesion
	 */
	public Puntuacion(Long id, Sesion sesion, Integer posicion, Integer puntos) {
		super();
		this.id = id;
		this.sesion = sesion;
		this.posicion = posicion;
		this.puntos = puntos;
	}

	/**
	 * Constructor por parametros sin id
	 * 
	 * @param reglamento
	 * @param posicion
	 * @param puntos
	 * @param tipoSesion
	 */
	public Puntuacion(Sesion sesion, Integer posicion, Integer puntos) {
		super();
		this.posicion = posicion;
		this.puntos = puntos;
		this.sesion = sesion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "#" + id + " - S[" + (sesion == null ? "null" : sesion) + "] " + posicion + "º -> " + puntos;
	}

	/**
	 * @return the sesion
	 */
	public Sesion getSesion() {
		return sesion;
	}

	/**
	 * @param sesion the sesion to set
	 */
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

}
