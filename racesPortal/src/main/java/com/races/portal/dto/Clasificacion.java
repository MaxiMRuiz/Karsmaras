package com.races.portal.dto;

public class Clasificacion {

	private Piloto piloto;
	private Equipo equipo;
	private Integer puntos;

	/**
	 * 
	 */
	public Clasificacion() {
		super();
	}

	/**
	 * @param piloto
	 * @param equipo
	 * @param puntos
	 */
	public Clasificacion(Piloto piloto, Equipo equipo, Integer puntos) {
		super();
		this.piloto = piloto;
		this.equipo = equipo;
		this.puntos = puntos;
	}

	/**
	 * @return the piloto
	 */
	public Piloto getPiloto() {
		return piloto;
	}

	/**
	 * @param piloto the piloto to set
	 */
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	/**
	 * @return the equipo
	 */
	public Equipo getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
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

}
