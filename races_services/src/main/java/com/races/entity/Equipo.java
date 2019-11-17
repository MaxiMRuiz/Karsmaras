package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.races.dto.EquipoDto;

@Entity
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String alias;

	public Equipo(Long id, String nombre, String alias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alias = alias;
	}

	public Equipo() {
		super();
	}

	public Equipo(EquipoDto equipoDto) {
		super();
		this.nombre=equipoDto.getNombre();
		this.alias=equipoDto.getAlias();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
