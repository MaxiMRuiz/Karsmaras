package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GranPremio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Campeonato campeonato;

	private String ubicacion;

	public GranPremio() {
		super();
	}

	public GranPremio(int id, Campeonato campeonato, String ubicacion) {
		super();
		this.id = id;
		this.campeonato = campeonato;
		this.ubicacion = ubicacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
