package com.races.dto;

public class CampeonatoDto {

	private String nombre;

	private String descripcion;

	private String temporada;

	private Long reglamento;

	public CampeonatoDto(String nombre, String descripcion, String temporada, Long reglamento) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.temporada = temporada;
		this.reglamento = reglamento;
	}

	public CampeonatoDto() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public Long getReglamento() {
		return reglamento;
	}

	public void setReglamento(Long reglamento) {
		this.reglamento = reglamento;
	}
	
}
