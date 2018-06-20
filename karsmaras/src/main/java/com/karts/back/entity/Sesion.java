package com.karts.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Sesion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	private ClasificacionPilotos clasiPilotos;
	
	@OneToOne
	private ClasificacionEquipos clasiEquipos;

	public int getSesionId() {
		return id;
	}

	public void setSesionId(int sesionId) {
		this.id = sesionId;
	}

	public ClasificacionPilotos getClasiPilotos() {
		return clasiPilotos;
	}

	public void setClasiPilotos(ClasificacionPilotos clasiPilotos) {
		this.clasiPilotos = clasiPilotos;
	}

	public ClasificacionEquipos getClasiEquipos() {
		return clasiEquipos;
	}

	public void setClasiEquipos(ClasificacionEquipos clasiEquipos) {
		this.clasiEquipos = clasiEquipos;
	}

	public Sesion() {
		super();
	}

	public Sesion(ClasificacionPilotos clasiPilotos, ClasificacionEquipos clasiEquipos) {
		super();
		this.clasiPilotos = clasiPilotos;
		this.clasiEquipos = clasiEquipos;
	}
	
}
