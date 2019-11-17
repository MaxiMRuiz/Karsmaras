package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Puntuacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_reglamento")
	private Reglamento reglamento;

	private Integer posicion;

	private Integer puntos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_sesion")
	private TipoSesion tipoSesion;

	public Puntuacion() {
		super();
	}

	public Puntuacion(Long id, Reglamento reglamento, Integer posicion, Integer puntos, TipoSesion tipoSesion) {
		super();
		this.id = id;
		this.reglamento = reglamento;
		this.posicion = posicion;
		this.puntos = puntos;
		this.tipoSesion = tipoSesion;
	}

	public Puntuacion(Reglamento reglamento, Integer posicion, Integer puntos, TipoSesion tipoSesion) {
		super();
		this.reglamento = reglamento;
		this.posicion = posicion;
		this.puntos = puntos;
		this.tipoSesion = tipoSesion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
