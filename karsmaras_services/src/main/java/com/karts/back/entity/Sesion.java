package com.karts.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	private Gp gp;

	private boolean puntua;

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

	public Gp getGp() {
		return gp;
	}

	public void setGp(Gp gp) {
		this.gp = gp;
	}

	public boolean isPuntua() {
		return puntua;
	}

	public void setPuntua(boolean puntua) {
		this.puntua = puntua;
	}

	public Sesion() {
		super();
	}

	public Sesion(ClasificacionPilotos clasiPilotos, ClasificacionEquipos clasiEquipos, Gp gp, boolean puntua) {
		super();
		this.clasiPilotos = clasiPilotos;
		this.clasiEquipos = clasiEquipos;
		this.gp = gp;
		this.puntua = puntua;
	}
}
