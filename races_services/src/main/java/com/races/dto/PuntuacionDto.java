package com.races.dto;

public class PuntuacionDto {
	
	private Long idReglamento;

	private Integer posicion;
	
	private Integer puntos;
	
	private Long idTipoSesion;

	public PuntuacionDto() {
		super();
	}

	public PuntuacionDto(Long idReglamento, Integer posicion, Integer puntos, Long idTipoSesion) {
		super();
		this.idReglamento = idReglamento;
		this.posicion = posicion;
		this.puntos = puntos;
		this.idTipoSesion = idTipoSesion;
	}

	public Long getIdReglamento() {
		return idReglamento;
	}

	public void setIdReglamento(Long idReglamento) {
		this.idReglamento = idReglamento;
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

	public Long getIdTipoSesion() {
		return idTipoSesion;
	}

	public void setIdTipoSesion(Long idTipoSesion) {
		this.idTipoSesion = idTipoSesion;
	}
	
}
