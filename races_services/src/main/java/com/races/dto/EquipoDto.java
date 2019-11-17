package com.races.dto;

public class EquipoDto {

	private String nombre;

	private String alias;

	public EquipoDto() {
		super();
	}

	public EquipoDto(String nombre, String alias) {
		super();
		this.nombre = nombre;
		this.alias = alias;
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
