package com.karsmaras.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Equipo {

	@Id
	private String alias;
	
	private String descripcion;
	
	@OneToOne
	private Piloto piloto1;
	
	@OneToOne
	private Piloto piloto2;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Piloto getPiloto1() {
		return piloto1;
	}

	public void setPiloto1(Piloto piloto1) {
		this.piloto1 = piloto1;
	}

	public Piloto getPiloto2() {
		return piloto2;
	}

	public void setPiloto2(Piloto piloto2) {
		this.piloto2 = piloto2;
	}

	public Equipo() {
		super();
	}

	public Equipo(String alias, String descripcion, Piloto piloto1, Piloto piloto2) {
		super();
		this.alias = alias;
		this.descripcion = descripcion;
		this.piloto1 = piloto1;
		this.piloto2 = piloto2;
	}
	
}
