package com.races.portal.dto;

public class Equipo {

	private Long id;

	private String nombre;

	private String alias;

	/**
	 * Constructor por defecto
	 */
	public Equipo() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param nombre
	 * @param alias
	 */
	public Equipo(Long id, String nombre, String alias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alias = alias;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return nombre + " (" + alias + ")";
	}

}
