package com.karts.back.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "campeonato")
public class Campeonato {

	@Id
	@Column(name = "idCampeonato", nullable = false)
	private int idCampeonato;
	
	@Column(name = "nombreCampeonato", nullable = false)
	private String nombreCampeonato;
	
	@Column(name = "idReglamento", nullable = false)
	private int idReglamento;
	
	@Column(name = "year", nullable = false)
	private int year;

	public int getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(int idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getNombreCampeonato() {
		return nombreCampeonato;
	}

	public void setNombreCampeonato(String nombreCampeonato) {
		this.nombreCampeonato = nombreCampeonato;
	}

	public int getIdReglamento() {
		return idReglamento;
	}

	public void setIdReglamento(int idReglamento) {
		this.idReglamento = idReglamento;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Campeonato(int idCampeonato, String nombreCampeonato, int idReglamento, int year) {
		super();
		this.idCampeonato = idCampeonato;
		this.nombreCampeonato = nombreCampeonato;
		this.idReglamento = idReglamento;
		this.year = year;
	}
	
	public Campeonato(String nombreCampeonato, int idReglamento, int year) {
		super();
		this.nombreCampeonato = nombreCampeonato;
		this.idReglamento = idReglamento;
		this.year = year;
	}

	public Campeonato() {
		super();
	}
	
	
}
