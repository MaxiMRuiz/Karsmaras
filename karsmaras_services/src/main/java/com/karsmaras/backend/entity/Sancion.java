package com.karsmaras.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sancion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String descripcion;
	
	private String gravedad;
	
	private int puntos;
	
	private int posiciones;
	
	@ManyToMany(mappedBy = "sanciones")
	@JsonIgnore
    private List<Reglamento> reglamentos = new ArrayList<>();
	
	@ManyToMany(mappedBy = "sanciones")
    @JsonIgnore
	private List<Resultado> resultados = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGravedad() {
		return gravedad;
	}

	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(int posiciones) {
		this.posiciones = posiciones;
	}

	public List<Reglamento> getReglamentos() {
		return reglamentos;
	}

	public void setReglamentos(List<Reglamento> reglamentos) {
		this.reglamentos = reglamentos;
	}

	public Sancion() {
		super();
	}
	
}
