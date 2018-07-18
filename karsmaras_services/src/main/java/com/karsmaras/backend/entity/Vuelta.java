package com.karsmaras.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vuelta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private double tiempo;
	
	private int nVuelta;
	
	@ManyToOne
	private Resultado resultado;

	public int getIdVuelta() {
		return id;
	}

	public void setIdVuelta(int idVuelta) {
		this.id = idVuelta;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public int getnVuelta() {
		return nVuelta;
	}

	public void setnVuelta(int nVuelta) {
		this.nVuelta = nVuelta;
	}


	public Vuelta(int idVuelta, double tiempo, int nVuelta, Resultado resultado) {
		super();
		this.id = idVuelta;
		this.tiempo = tiempo;
		this.nVuelta = nVuelta;
		this.resultado = resultado;
	}
	
	public Vuelta(double tiempo, int nVuelta, Resultado resultado) {
		super();
		this.tiempo = tiempo;
		this.nVuelta = nVuelta;
		this.resultado = resultado;
	}

	public Vuelta() {
		super();
	}
	
	
	
}
