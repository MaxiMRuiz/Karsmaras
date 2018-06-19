package com.karts.back.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "puesto")
public class Puesto {

	@Id
	@Column(name = "idPuesto", nullable = false)
	private int idPuesto;
	
	@Column(name = "piloto", nullable = false)
	private String piloto;
	
	@Column(name = "puntos", nullable = false)
	private int puntos;

	public int getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getPiloto() {
		return piloto;
	}

	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Puesto(int idPuesto, String piloto, int puntos) {
		super();
		this.idPuesto = idPuesto;
		this.piloto = piloto;
		this.puntos = puntos;
	}

	public Puesto() {
		super();
	}
	
}
