package com.races.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sesion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Date fecha;

	@ManyToOne
	private GranPremio granPremio;

	@ManyToOne
	private TipoSesion tipoSesion;

	public Sesion() {
		super();
	}

	public Sesion(Integer id, Date fecha, GranPremio granPremio, TipoSesion tipoSesion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.granPremio = granPremio;
		this.tipoSesion = tipoSesion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public GranPremio getGranPremio() {
		return granPremio;
	}

	public void setGranPremio(GranPremio granPremio) {
		this.granPremio = granPremio;
	}

	public TipoSesion getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(TipoSesion tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

}
