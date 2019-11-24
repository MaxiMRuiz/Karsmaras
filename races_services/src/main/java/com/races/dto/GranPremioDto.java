package com.races.dto;

/**
 * Dto para Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class GranPremioDto {

	private Long idCampeonato;

	private String ubicacion;

	/**
	 * Constructor por defecto
	 */
	public GranPremioDto() {
		super();
	}

	/**
	 * Constructor con parametros
	 * 
	 * @param idCampeonato
	 * @param ubicacion
	 */
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
