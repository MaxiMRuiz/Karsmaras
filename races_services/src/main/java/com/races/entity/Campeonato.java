package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Campeonato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private String temporada;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_reglamento")
	private Reglamento reglamento;

	public Campeonato() {
		super();
	}

	public Campeonato(Long id, String nombre, String descripcion, String temporada, Reglamento reglamento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.temporada = temporada;
		this.reglamento = reglamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public Reglamento getReglamento() {
		return reglamento;
	}

	public void setReglamento(Reglamento reglamento) {
		this.reglamento = reglamento;
	}
	
}
