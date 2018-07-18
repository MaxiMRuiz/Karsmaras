package com.karts.back.dto;

public class PuntosPosicionDTO {

	private int posicion;
	
	private int puntos;

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public PuntosPosicionDTO() {
		super();
	}

	public PuntosPosicionDTO(int posicion, int puntos) {
		super();
		this.posicion = posicion;
		this.puntos = puntos;
	}
	
}
