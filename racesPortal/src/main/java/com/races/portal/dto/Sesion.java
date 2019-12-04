package com.races.portal.dto;

public class Sesion {

	private Long id;
	private String fecha;
	private TipoSesion tipoSesion;

	/**
	 * 
	 */
	public Sesion() {
		super();
	}

	/**
	 * @param id
	 * @param fecha
	 * @param tipoSesion
	 */
	public Sesion(Long id, String fecha, TipoSesion tipoSesion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipoSesion = tipoSesion;
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
	public TipoSesion getTipoSesion() {
		return tipoSesion;
	}

	/**
	 * @param tipoSesion the tipoSesion to set
	 */
	public void setTipoSesion(TipoSesion tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	@Override
	public String toString() {
		return (tipoSesion==null? "":tipoSesion.getDescripcion()) + " - " + fecha;
	}

}
