package com.races.dto;

import java.sql.Date;

/**
 * Dto para Sesiones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class SesionGpDto {

	private Date fecha;

	private Long idGranPremio;

	private Long idSesion;

	/**
	 * Constructor por defecto
	 */
	public SesionGpDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param fecha
	 * @param idGranPremio
	 * @param idTipoSesion
	 */
	public SesionGpDto(Date fecha, Long idGranPremio, Long idSesion) {
		super();
		this.fecha = fecha;
		this.idGranPremio = idGranPremio;
		this.idSesion = idSesion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdGranPremio() {
		return idGranPremio;
	}

	public void setIdGranPremio(Long idGranPremio) {
		this.idGranPremio = idGranPremio;
	}

	public Long getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
	}

}
