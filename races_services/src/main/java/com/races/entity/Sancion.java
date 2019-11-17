package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sancion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Resultado resultado;

	private String descripcion;

	private Integer puntos;

	private Integer tiempo;

	public Sancion() {
		super();
	}

	public Sancion(Long id, Resultado resultado, String descripcion, Integer puntos, Integer tiempo) {
		super();
		this.id = id;
		this.resultado = resultado;
		this.descripcion = descripcion;
		this.puntos = puntos;
		this.tiempo = tiempo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

}
