package com.karts.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Piloto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String alias;
	
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

	public Piloto(String alias, String nombre) {
		super();
		this.alias = alias;
		this.nombre = nombre;
	}

	public Piloto() {
		super();
	}
	
}
