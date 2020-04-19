package com.races.portal.dto;

import java.util.List;

public class GranPremio {

	private Long id;
	private String ubicacion;
	List<SesionGP> sesiones;
	private String fecha;

	/**
	 * @param id
	 * @param ubicacion
	 */
	public GranPremio(Long id, String ubicacion, String fecha) {
		super();
		this.id = id;
		this.ubicacion = ubicacion;
		this.fecha = fecha;
	}

	/**
	 * 
	 */
	public GranPremio() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "GP " + ubicacion;
	}

	/**
	 * @return the sesiones
	 */
	public List<SesionGP> getSesiones() {
		return sesiones;
	}

	/**
	 * @param sesiones the sesiones to set
	 */
	public void setSesiones(List<SesionGP> sesiones) {
		this.sesiones = sesiones;
	}

	/**
	 * @return
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * 
	 * @param fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
