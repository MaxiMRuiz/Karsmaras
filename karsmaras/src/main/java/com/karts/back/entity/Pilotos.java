package com.karts.back.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="pilotos")
public class Pilotos {

	@Id
	@Column(name = "alias", nullable = false)
	private String alias;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pilotos(String alias, String nombre) {
		super();
		this.alias = alias;
		this.nombre = nombre;
	}

	public Pilotos() {
		super();
	}
	
}
