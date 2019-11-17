package com.races.dto;

import java.sql.Date;

public class SesionDto {

	private Date fecha;

	private Long idGranPremio;

	private Long idTipoSesion;

	public SesionDto() {
		super();
	}

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
