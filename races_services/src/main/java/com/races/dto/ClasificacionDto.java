package com.races.dto;

import com.races.entity.Inscripcion;

public class ClasificacionDto implements Comparable<ClasificacionDto> {

	private Inscripcion inscripcion;

	private Integer puntos;

	private Integer[] puestos;

	/**
	 * 
	 * @param piloto
	 * @param puntos
	 * @param puestos
	 * @param puesto
	 */
	public ClasificacionDto(Inscripcion inscripcion, Integer puntos, Integer[] puestos, Integer puesto, boolean isCarrera) {
		super();
		this.inscripcion = inscripcion;
		this.puntos = puntos;
		this.puestos = puestos;
		for(int i =0 ; i < puestos.length; i++) {
			this.puestos[i]=0;
		}
		if (isCarrera && puesto < puestos.length) {
			this.puestos[puesto] = 1;
		}
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
		int i = 0;
		while (this.puestos.length > i) {
			if (puestos[i] < o.getPuestos()[i]) {
				return 1;
			}
			if (puestos[i] > o.getPuestos()[i]) {
				return -1;
			}
			i++;
		}
		return 0;
	}

	public Integer[] getPuestos() {
		return puestos;
	}

	public void setPuestos(Integer[] puestos) {
		this.puestos = puestos;
	}

	public void addPuesto(Integer posicion) {
		this.puestos[posicion] = this.puestos[posicion] + 1;
	}

	public void mergePuestos(Integer[] puestos2) {
		for (int i = 0; i < this.puestos.length && i < puestos2.length; i++) {

			this.puestos[i] = this.puestos[i] + puestos2[i];
		}
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

}
