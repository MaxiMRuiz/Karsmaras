package com.races.dto;

public class ResultadoDto {

	private Long idPiloto;

	private Long idSesion;

	private Integer nVueltas;

	private Integer posicion;

	private Integer tiempo;

	public ResultadoDto() {
		super();
	}

	public ResultadoDto(Long idPiloto, Long idSesion, Integer nVueltas, Integer posicion, Integer tiempo) {
		super();
		this.idPiloto = idPiloto;
		this.idSesion = idSesion;
		this.nVueltas = nVueltas;
		this.posicion = posicion;
		this.tiempo = tiempo;
	}

	public Long getIdPiloto() {
		return idPiloto;
	}

	public void setIdPiloto(Long idPiloto) {
		this.idPiloto = idPiloto;
	}

	public Long getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
	}

	public Integer getnVueltas() {
		return nVueltas;
	}

	public void setnVueltas(Integer nVueltas) {
		this.nVueltas = nVueltas;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

}
