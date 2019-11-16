package com.races.auth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Puntuacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Reglamento reglamento;

	private Integer posicion;
	
	private Integer puntos;
	
	@ManyToOne
	private TipoSesion tipoSesion;

	public Puntuacion() {
		super();
	}

	public Puntuacion(int id, Reglamento reglamento, Integer posicion, Integer puntos, TipoSesion tipoSesion) {
		super();
		this.id = id;
		this.reglamento = reglamento;
		this.posicion = posicion;
		this.puntos = puntos;
		this.tipoSesion = tipoSesion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reglamento getReglamento() {
		return reglamento;
	}

	public void setReglamento(Reglamento reglamento) {
		this.reglamento = reglamento;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public TipoSesion getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(TipoSesion tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

}
