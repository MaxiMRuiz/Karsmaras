package com.races.dto;

import java.util.Date;

/**
 * Dto para Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class GranPremioDto {

	private Long idCampeonato;

	private String ubicacion;
	
	private Date fecha;

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
	public GranPremioDto(Long idCampeonato, String ubicacion, Date fecha) {
		super();
		this.idCampeonato = idCampeonato;
		this.ubicacion = ubicacion;
		this.fecha = fecha;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
