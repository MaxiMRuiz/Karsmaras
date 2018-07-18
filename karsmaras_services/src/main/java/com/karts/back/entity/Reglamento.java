package com.karts.back.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Reglamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int puntosPole;
	private int puntosVR;
	private String normativaParrilla;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "reglamento_posiciones", joinColumns = {
			@JoinColumn(name = "reglamento_id") }, inverseJoinColumns = { @JoinColumn(name = "puntos_posicion_id") })
	private List<PuntosPosicion> puntosPosicion = new ArrayList<>();
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "reglamento_sanciones", joinColumns = {
			@JoinColumn(name = "reglamento_id") }, inverseJoinColumns = { @JoinColumn(name = "sancion_id") })
	private List<Sancion> sanciones = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuntosPole() {
		return puntosPole;
	}

	public void setPuntosPole(int puntosPole) {
		this.puntosPole = puntosPole;
	}

	public String getNormativaParrilla() {
		return normativaParrilla;
	}

	public void setNormativaParrilla(String normativaParrilla) {
		this.normativaParrilla = normativaParrilla;
	}

	public int getPuntosVR() {
		return puntosVR;
	}

	public void setPuntosVR(int puntosVR) {
		this.puntosVR = puntosVR;
	}

	public Reglamento() {
		super();
	}

	public List<PuntosPosicion> getPuntosPosicion() {
		return puntosPosicion;
	}

	public void setPuntosPosicion(List<PuntosPosicion> puntosPosicion) {
		this.puntosPosicion = puntosPosicion;
	}

	public List<Sancion> getSanciones() {
		return sanciones;
	}

	public void setSanciones(List<Sancion> sanciones) {
		this.sanciones = sanciones;
	}

	public Reglamento(int id, int puntosPole, int puntosVR, String normativaParrilla,
			List<PuntosPosicion> puntosPosicion, List<Sancion> sanciones) {
		super();
		this.id = id;
		this.puntosPole = puntosPole;
		this.puntosVR = puntosVR;
		this.normativaParrilla = normativaParrilla;
		this.puntosPosicion = puntosPosicion;
		this.sanciones = sanciones;
	}

}
