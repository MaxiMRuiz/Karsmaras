package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.races.dto.PilotoDto;

@Entity
public class Piloto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String apellido;

	private String apodo;

	public Piloto(Long id, String nombre, String apellido, String apodo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apodo = apodo;
	}

	public Piloto() {
		super();
	}

	public Piloto(PilotoDto pilotoDto) {
		super();
		this.nombre = pilotoDto.getNombre();
		this.apellido = pilotoDto.getApellido();
		this.apodo = pilotoDto.getApodo();
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
