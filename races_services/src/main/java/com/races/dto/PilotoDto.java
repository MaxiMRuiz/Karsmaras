package com.races.dto;

/**
 * Dto para pilotos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class PilotoDto {

	private String nombre;

	private String apellido;

	private String apodo;

	/**
	 * Constructor por defecto
	 */
	public PilotoDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param nombre
	 * @param apellido
	 * @param apodo
	 */
	public PilotoDto(String nombre, String apellido, String apodo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.apodo = apodo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

}
