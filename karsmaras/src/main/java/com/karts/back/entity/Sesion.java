package com.karts.back.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sesion")
public class Sesion {

	@Id
	@Column(name = "sesionId")
	private int sesionId;

	@Column(name = "idClasificacionSingle", nullable = false)
	private int idClasificacionSingle;
	
	@Column(name = "idClasificacionTeams", nullable = false)
	private int idClasificacionTeams;

	public int getSesionId() {
		return sesionId;
	}

	public void setSesionId(int sesionId) {
		this.sesionId = sesionId;
	}

	public int getIdClasificacionSingle() {
		return idClasificacionSingle;
	}

	public void setIdClasificacionSingle(int idClasificacionSingle) {
		this.idClasificacionSingle = idClasificacionSingle;
	}

	public int getIdClasificacionTeams() {
		return idClasificacionTeams;
	}

	public void setIdClasificacionTeams(int idClasificacionTeams) {
		this.idClasificacionTeams = idClasificacionTeams;
	}

	public Sesion(int sesionId, int idClasificacionSingle, int idClasificacionTeams) {
		super();
		this.sesionId = sesionId;
		this.idClasificacionSingle = idClasificacionSingle;
		this.idClasificacionTeams = idClasificacionTeams;
	}

	public Sesion(int idClasificacionSingle, int idClasificacionTeams) {
		super();
		this.idClasificacionSingle = idClasificacionSingle;
		this.idClasificacionTeams = idClasificacionTeams;
	}

	
	public Sesion() {
		super();
	}
	
}
