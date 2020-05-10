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

	private String password;
	
	private Boolean admin;

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
	public PilotoDto(String nombre, String apellido, String apodo, String password, Boolean admin) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.apodo = apodo;
		this.password = password;
		this.admin = admin;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
