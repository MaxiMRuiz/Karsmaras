package com.races.portal.dto;

public class Clasificacion implements Comparable<Clasificacion> {

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
	 * 
	 * @param clasificacion
	 */
	public Clasificacion(Clasificacion clasificacion) {
		super();
		this.piloto = clasificacion.getPiloto();
		this.equipo = clasificacion.getEquipo();
		this.puntos = clasificacion.getPuntos();
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

	/**
	 * Add puntos
	 * 
	 * @param puntos
	 */
	public void addPuntos(Integer puntos) {
		this.puntos = this.puntos + puntos;
	}

	@Override
	public int compareTo(Clasificacion arg0) {
		if (arg0 != null && arg0.getPuntos() != null && arg0.getPuntos() < this.puntos) {
			return -1;
		}
		if (arg0 != null && arg0.getPuntos() != null && arg0.getPuntos() > this.puntos) {
			return 1;
		}
		return 0;
	}

}
