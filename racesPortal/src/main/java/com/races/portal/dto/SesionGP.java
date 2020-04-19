package com.races.portal.dto;

public class SesionGP {

	private Long id;
	private String fecha;
	private Sesion sesion;

	/**
	 * 
	 */
	public SesionGP() {
		super();
	}

	/**
	 * @param id
	 * @param fecha
	 * @param tipoSesion
	 */
	public SesionGP(Long id, String fecha, Sesion sesion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.sesion = sesion;
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
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the tipoSesion
	 */
	public Sesion getSesion() {
		return sesion;
	}

	/**
	 * @param tipoSesion the tipoSesion to set
	 */
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	@Override
	public String toString() {
		return (sesion==null? "":sesion.getDescripcion()) + " - " + fecha;
	}

}
