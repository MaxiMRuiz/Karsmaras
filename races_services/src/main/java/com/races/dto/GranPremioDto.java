package com.races.dto;

public class GranPremioDto {

	private Long idCampeonato;

	private String ubicacion;

	public GranPremioDto() {
		super();
	}

	public GranPremioDto(Long idCampeonato, String ubicacion) {
		super();
		this.idCampeonato = idCampeonato;
		this.ubicacion = ubicacion;
	}

	public Long getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(Long idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
