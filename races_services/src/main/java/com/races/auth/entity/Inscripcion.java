package com.races.auth.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Inscripcion {

	@ManyToOne
	private Campeonato campeonato;

	@ManyToOne
	private Piloto piloto;

	@ManyToOne
	private Equipo equipo;

	public Inscripcion() {
		super();
	}

	public Inscripcion(Campeonato campeonato, Piloto piloto, Equipo equipo) {
		super();
		this.campeonato = campeonato;
		this.piloto = piloto;
		this.equipo = equipo;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}
