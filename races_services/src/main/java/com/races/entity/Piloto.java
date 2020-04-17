package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.races.dto.PilotoDto;

/**
 * Entidad de la tabla Piloto
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Entity
public class Piloto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String apellido;

	private String apodo;

	@JsonIgnore
	private String password;

	private Boolean admin;

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param apodo
	 */
	public Piloto(Long id, String nombre, String apellido, String apodo, String password, Boolean admin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apodo = apodo;
		this.password = password;
		this.admin = admin;
	}

	/**
	 * Constructor por defecto
	 */
	public Piloto() {
		super();
	}

	/**
	 * Constructor por DTO
	 * 
	 * @param pilotoDto
	 */
	public Piloto(PilotoDto pilotoDto) {
		super();
		this.nombre = pilotoDto.getNombre();
		this.apellido = pilotoDto.getApellido();
		this.apodo = pilotoDto.getApodo();
		this.password = pilotoDto.getPassword();
		this.admin = pilotoDto.getAdmin();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "#" + id + " - " + nombre + " " + apellido + " (" + apodo + ")";
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
