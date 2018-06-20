package com.karts.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Resultado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Piloto piloto;
	
	private int kart;
	
	@ManyToOne
	private Sesion sesion;
	
	private int posicion;

	public int getIdResultado() {
		return id;
	}

	public void setIdResultado(int idResultado) {
		this.id = idResultado;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public int getKart() {
		return kart;
	}

	public void setKart(int kart) {
		this.kart = kart;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Resultado() {
		super();
	}

	public Resultado(Piloto piloto, int kart, Sesion sesion, int posicion) {
		super();
		this.piloto = piloto;
		this.kart = kart;
		this.sesion = sesion;
		this.posicion = posicion;
	}
	
}
