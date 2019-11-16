package com.races.auth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipo {

	@Id
	private String id;
	
	private String nombre;
	
	private String alias;

	public Equipo(String id, String nombre, String alias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alias = alias;
	}

	public Equipo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
