package com.races.dto;

import java.sql.Date;

/**
 * Dto para Sesiones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class SesionDto {

	private Date fecha;

	private Long idGranPremio;

	private Long idTipoSesion;

	/**
	 * Constructor por defecto
	 */
	public SesionDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param fecha
	 * @param idGranPremio
	 * @param idTipoSesion
	 */
	public SesionDto(Date fecha, Long idGranPremio, Long idTipoSesion) {
		super();
		this.fecha = fecha;
		this.idGranPremio = idGranPremio;
		this.idTipoSesion = idTipoSesion;
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

	public Long getIdTipoSesion() {
		return idTipoSesion;
	}

	public void setIdTipoSesion(Long idTipoSesion) {
		this.idTipoSesion = idTipoSesion;
	}

}
