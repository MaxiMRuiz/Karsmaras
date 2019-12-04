package com.races.portal.dto;

/**
 * DTO de Pilotos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class Piloto {

	private Long id;

	private String nombre;

	private String apellido;

	private String apodo;

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
	public Piloto(Long id, String nombre, String apellido, String apodo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apodo = apodo;
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
