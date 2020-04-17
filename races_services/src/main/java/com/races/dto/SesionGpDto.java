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
	public SesionGpDto(Date fecha) {
		super();
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
