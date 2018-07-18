package com.karsmaras.backend.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Gp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Date fecha;

	@OneToOne
	private ClasificacionEquipos clasiEquipos;

	@ManyToOne
	private Campeonato campeonatoEquipos;

	@OneToOne
	private ClasificacionPilotos clasiPilotos;

	@ManyToOne
	private Campeonato campeonatoPilotos;

	public int getIdGp() {
		return id;
	}

	public void setIdGp(int idGp) {
		this.id = idGp;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ClasificacionEquipos getClasiEquipos() {
		return clasiEquipos;
	}

	public void setClasiEquipos(ClasificacionEquipos clasiEquipos) {
		this.clasiEquipos = clasiEquipos;
	}

	public Campeonato getCampeonatoEquipos() {
		return campeonatoEquipos;
	}

	public void setCampeonatoEquipos(Campeonato campeonatoEquipos) {
		this.campeonatoEquipos = campeonatoEquipos;
	}

	public ClasificacionPilotos getClasiPilotos() {
		return clasiPilotos;
	}

	public void setClasiPilotos(ClasificacionPilotos clasiPilotos) {
		this.clasiPilotos = clasiPilotos;
	}

	public Campeonato getCampeonatoPilotos() {
		return campeonatoPilotos;
	}

	public void setCampeonatoPilotos(Campeonato campeonatoPilotos) {
		this.campeonatoPilotos = campeonatoPilotos;
	}

	public Gp() {
		super();
	}

	public Gp(Date fecha, ClasificacionEquipos clasiEquipos, Campeonato campeonatoEquipos,
			ClasificacionPilotos clasiPilotos, Campeonato campeonatoPilotos) {
		this.fecha = fecha;
		this.clasiEquipos = clasiEquipos;
		this.campeonatoEquipos = campeonatoEquipos;
		this.clasiPilotos = clasiPilotos;
		this.campeonatoPilotos = campeonatoPilotos;
	}

}
