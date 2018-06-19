package com.karts.back.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "vueltas")
public class Vueltas {

	@Id
	@Column(name = "idVuelta", nullable = false)
	private int idVuelta;
	
	@Column(name = "tiempo", nullable = false)
	private double tiempo;
	
	@Column(name = "nVuelta", nullable = false)
	private int nVuelta;
	
	@Column(name = "idResultado", nullable = false)
	private int idResultado;

	public int getIdVuelta() {
		return idVuelta;
	}

	public void setIdVuelta(int idVuelta) {
		this.idVuelta = idVuelta;
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

	public int getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}

	public Vueltas(int idVuelta, double tiempo, int nVuelta, int idResultado) {
		super();
		this.idVuelta = idVuelta;
		this.tiempo = tiempo;
		this.nVuelta = nVuelta;
		this.idResultado = idResultado;
	}
	
	public Vueltas(double tiempo, int nVuelta, int idResultado) {
		super();
		this.tiempo = tiempo;
		this.nVuelta = nVuelta;
		this.idResultado = idResultado;
	}

	public Vueltas() {
		super();
	}
	
	
	
}
