package com.races.dto;

import com.races.entity.Piloto;

public class ClasificacionDto implements Comparable<ClasificacionDto> {

	private Piloto piloto;

	private Integer puntos;

	/**
	 * 
	 */
	public ClasificacionDto() {
		super();
	}

	/**
	 * @param piloto
	 * @param puntos
	 */
	public ClasificacionDto(Piloto piloto, Integer puntos) {
		super();
		this.piloto = piloto;
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

	@Override
	public int compareTo(ClasificacionDto o) {
		if (puntos < o.getPuntos()) {
			return 1;
		}
		if (puntos > o.getPuntos()) {
			return -1;
		}
		return 0;
	}

}
