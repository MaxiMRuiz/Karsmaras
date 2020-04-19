package com.races.portal.dto;

import java.io.Serializable;

/**
 * DTO de Pilotos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class Piloto implements Serializable {

	/**
	 * Default Serial Version
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nombre;

	private String apellido;

	private String apodo;

	private String password;

	private Boolean admin;

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

	/**
	 * @return the admin
	 */
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * Default constructor
	 */
	public Piloto() {
		super();
	}

	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param apodo
	 */
	public Piloto(Long id, String nombre, String apellido, String apodo, String password, Boolean admin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apodo = apodo;
		this.password = password;
		this.admin = admin;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the apodo
	 */
	public String getApodo() {
		return apodo;
	}

	/**
	 * @param apodo the apodo to set
	 */
	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido + " (" + apodo + ")";
	}

}
