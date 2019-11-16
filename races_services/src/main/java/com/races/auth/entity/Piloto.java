package com.races.auth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Piloto {

	@Id
	private String id;

	private String nombre;

	private String apellido;

	private String apodo;

	public Piloto(String id, String nombre, String apellido, String apodo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apodo = apodo;
	}

	public Piloto() {
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
