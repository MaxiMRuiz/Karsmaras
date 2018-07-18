package com.karsmaras.backend.dto;

public class PilotoDTO {

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

	public PilotoDTO(String alias, String nombre) {
		super();
		this.alias = alias;
		this.nombre = nombre;
	}

	public PilotoDTO() {
		super();
	}

	@Override
	public String toString() {
		return "PilotoDTO [alias=" + alias + ", nombre=" + nombre + "]";
	}
	
}
