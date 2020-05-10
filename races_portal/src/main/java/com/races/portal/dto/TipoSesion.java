package com.races.portal.dto;

public class TipoSesion {

	private Long id;

	private String descripcion;

	/**
	 * 
	 */
	public TipoSesion() {
		super();
	}

	/**
	 * @param id
	 * @param descripcion
	 */
	public TipoSesion(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return descripcion + " (" + id + ")";
	}

}
