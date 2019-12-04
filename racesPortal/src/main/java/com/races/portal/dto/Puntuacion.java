package com.races.portal.dto;

public class Puntuacion {

	private Long id;

	private Integer posicion;

	private Integer puntos;

	private TipoSesion tipoSesion;

	/**
	 * 
	 */
	public Puntuacion() {
		super();
	}

	/**
	 * @param id
	 * @param posicion
	 * @param puntos
	 * @param sesion
	 */
	public Puntuacion(Long id, Integer posicion, Integer puntos, TipoSesion tipoSesion) {
		super();
		this.id = id;
		this.posicion = posicion;
		this.puntos = puntos;
		this.tipoSesion = tipoSesion;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the posicion
	 */
	public Integer getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the puntos
	 */
	public Integer getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	/**
	 * @return the sesion
	 */
	public TipoSesion getTipoSesion() {
		return tipoSesion;
	}

	/**
	 * @param sesion the sesion to set
	 */
	public void setTipoSesion(TipoSesion tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	@Override
	public String toString() {
		return tipoSesion + ": " + posicion + "º -> " + puntos + "p.";
	}

}
