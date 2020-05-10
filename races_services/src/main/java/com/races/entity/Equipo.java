package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.races.dto.EquipoDto;

/**
 * Entidad de la tabla Equipo
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Entity
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String alias;

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param nombre
	 * @param alias
	 */
	public Equipo(Long id, String nombre, String alias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alias = alias;
	}

	/**
	 * Constructor por defecto
	 */
	public Equipo() {
		super();
	}

	/**
	 * Constructor por DTO
	 * 
	 * @param equipoDto
	 */
	public Equipo(EquipoDto equipoDto) {
		super();
		this.nombre = equipoDto.getNombre();
		this.alias = equipoDto.getAlias();
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

	@Override
	public String toString() {
		return "#" + id + " - " + nombre + " (" + alias + ")";
	}

}
