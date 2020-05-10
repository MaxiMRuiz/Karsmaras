package com.races.portal.dto;

public class Sesion {

	private Long id;
	private String descripcion;
	private TipoSesion tipoSesion;
	private Reglamento reglamento;

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
	public Sesion(Long id, String descripcion, TipoSesion tipoSesion, Reglamento reglamento) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.tipoSesion = tipoSesion;
		this.setReglamento(reglamento);
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
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		return this.descripcion;
	}

	public Reglamento getReglamento() {
		return reglamento;
	}

	public void setReglamento(Reglamento reglamento) {
		this.reglamento = reglamento;
	}

}
