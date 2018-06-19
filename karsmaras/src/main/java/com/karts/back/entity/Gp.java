package com.karts.back.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gp")
public class Gp {

	@Id
	@Column(name = "idGp", nullable = false)
	private int idGp;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@Column(name = "clasificacion", nullable = false)
	private int clasificacion;

	@Column(name = "carrera1", nullable = false)
	private int carrera1;

	@Column(name = "carrera2", nullable = false)
	private int carrera2;

	@Column(name = "idResultadoSingle", nullable = false)
	private int idResultadoSingle;

	@Column(name = "idCampeonatoSingle", nullable = false)
	private int idCampeonatoSingle;

	@Column(name = "idResultadoTeams", nullable = false)
	private int idResultadoTeams;

	@Column(name = "idCampeonatoTeams", nullable = false)
	private int idCampeonatoTeams;

	public int getIdGp() {
		return idGp;
	}

	public void setIdGp(int idGp) {
		this.idGp = idGp;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public int getCarrera1() {
		return carrera1;
	}

	public void setCarrera1(int carrera1) {
		this.carrera1 = carrera1;
	}

	public int getCarrera2() {
		return carrera2;
	}

	public void setCarrera2(int carrera2) {
		this.carrera2 = carrera2;
	}

	public int getIdResultadoSingle() {
		return idResultadoSingle;
	}

	public void setIdResultadoSingle(int idResultadoSingle) {
		this.idResultadoSingle = idResultadoSingle;
	}

	public int getIdCampeonatoSingle() {
		return idCampeonatoSingle;
	}

	public void setIdCampeonatoSingle(int idCampeonatoSingle) {
		this.idCampeonatoSingle = idCampeonatoSingle;
	}

	public int getIdResultadoTeams() {
		return idResultadoTeams;
	}

	public void setIdResultadoTeams(int idResultadoTeams) {
		this.idResultadoTeams = idResultadoTeams;
	}

	public int getIdCampeonatoTeams() {
		return idCampeonatoTeams;
	}

	public void setIdCampeonatoTeams(int idCampeonatoTeams) {
		this.idCampeonatoTeams = idCampeonatoTeams;
	}

	public Gp() {
		super();
	}	
	
}
