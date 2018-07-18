package com.karsmaras.backend.dto;

public class SancionesDTO {

	private String descripcion;

	private String gravedad;

	private int puntos;

	private int posiciones;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGravedad() {
		return gravedad;
	}

	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(int posiciones) {
		this.posiciones = posiciones;
	}

	public SancionesDTO(String descripcion, String gravedad, int puntos, int posiciones) {
		super();
		this.descripcion = descripcion;
		this.gravedad = gravedad;
		this.puntos = puntos;
		this.posiciones = posiciones;
	}

	public SancionesDTO() {
		super();
	}
	
}
