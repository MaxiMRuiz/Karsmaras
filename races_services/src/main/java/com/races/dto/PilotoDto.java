package com.races.dto;

public class PilotoDto {

	private String nombre;

	private String apellido;

	private String apodo;

	public PilotoDto() {
		super();
	}

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
