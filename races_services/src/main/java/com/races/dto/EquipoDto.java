package com.races.dto;

/**
 * Dto para equipos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class EquipoDto {

	private String nombre;

	private String alias;

	/**
	 * Constructor por defecto
	 */
	public EquipoDto() {
		super();
	}

	/**
	 * Constructor con parametros
	 * 
	 * @param nombre
	 * @param alias
	 */
	public EquipoDto(String nombre, String alias) {
		super();
		this.nombre = nombre;
		this.alias = alias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
