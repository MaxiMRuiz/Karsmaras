package com.races.dto;

import java.util.List;

import com.races.entity.GranPremio;
import com.races.entity.Sesion;

public class GranPremioSesionesDto {

	GranPremio gp;

	List<Sesion> sesiones;

	/**
	 * 
	 */
	public GranPremioSesionesDto() {
		super();
	}

	/**
	 * @param gp
	 * @param sesiones
	 */
	public GranPremioSesionesDto(GranPremio gp, List<Sesion> sesiones) {
		super();
		this.gp = gp;
		this.sesiones = sesiones;
	}

	/**
	 * @return the gp
	 */
	public GranPremio getGp() {
		return gp;
	}

	/**
	 * @param gp the gp to set
	 */
	public void setGp(GranPremio gp) {
		this.gp = gp;
	}

	/**
	 * @return the sesiones
	 */
	public List<Sesion> getSesiones() {
		return sesiones;
	}

	/**
	 * @param sesiones the sesiones to set
	 */
	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

}
