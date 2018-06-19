package com.karts.back.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="reglamento")
public class Reglamento {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "piloto", nullable = false)
	private String piloto;
	
	@Column(name = "kart")
	private int kart;
	
	@Column(name = "idSesion", nullable = false)
	private int idSesion;
	
	@Column(name = "posicion", nullable = false)
	private int posicion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPiloto() {
		return piloto;
	}

	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}

	public int getKart() {
		return kart;
	}

	public void setKart(int kart) {
		this.kart = kart;
	}

	public int getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Reglamento() {
		super();
	}

	public Reglamento(String piloto, int kart, int idSesion, int posicion) {
		super();
		this.piloto = piloto;
		this.kart = kart;
		this.idSesion = idSesion;
		this.posicion = posicion;
	}
	
}
