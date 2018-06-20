package com.karts.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Campeonato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nombreCampeonato;
	
	@ManyToOne
	private Reglamento reglamento;

	@OneToOne
	private ClasificacionPilotos clasificacionPilotos;
	
	@OneToOne
	private ClasificacionEquipos clasificacionEquipos;
	
	private int year;

	public int getIdCampeonato() {
		return id;
	}

	public void setIdCampeonato(int idCampeonato) {
		this.id = idCampeonato;
	}

	public String getNombreCampeonato() {
		return nombreCampeonato;
	}

	public void setNombreCampeonato(String nombreCampeonato) {
		this.nombreCampeonato = nombreCampeonato;
	}

	public Reglamento getIdReglamento() {
		return reglamento;
	}

	public void setIdReglamento(Reglamento reglamento) {
		this.reglamento = reglamento;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Campeonato(int idCampeonato, String nombreCampeonato, Reglamento reglamento, int year) {
		super();
		this.id = idCampeonato;
		this.nombreCampeonato = nombreCampeonato;
		this.reglamento = reglamento;
		this.year = year;
	}
	
	public Campeonato(String nombreCampeonato, Reglamento reglamento, int year) {
		super();
		this.nombreCampeonato = nombreCampeonato;
		this.reglamento = reglamento;
		this.year = year;
	}

	public Campeonato() {
		super();
	}
	
	
}
