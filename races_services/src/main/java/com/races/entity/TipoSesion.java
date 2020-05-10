package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidad de la tabla TipoSesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Entity
public class TipoSesion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String descripcion;

	/**
	 * Constructor por defecto
	 */
	public TipoSesion() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param descripcion
	 */
	public TipoSesion(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "#" + id + " - " + descripcion;
	}

}
