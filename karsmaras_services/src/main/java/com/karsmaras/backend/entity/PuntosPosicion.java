package com.karsmaras.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="puntos_posicion")
public class PuntosPosicion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int puntos;
	
	private int posicion;
	
	@ManyToMany(mappedBy = "puntosPosicion")
    @JsonIgnore
	private List<Reglamento> reglamentos = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public List<Reglamento> getReglamentos() {
		return reglamentos;
	}

	public void setReglamentos(List<Reglamento> reglamentos) {
		this.reglamentos = reglamentos;
	}

	public PuntosPosicion() {
		super();
	}
	
	
}
