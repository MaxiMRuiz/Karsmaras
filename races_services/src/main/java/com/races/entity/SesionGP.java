package com.races.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidad de la tabla Sesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Entity
public class SesionGP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date fecha;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_gp")
	private GranPremio granPremio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sesion")
	private Sesion sesion;

	/**
	 * Constructor por defecto
	 */
	public SesionGP() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param fecha
	 * @param granPremio
	 * @param tipoSesion
	 */
	public SesionGP(Long id, Date fecha, GranPremio granPremio, Sesion sesion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.granPremio = granPremio;
		this.sesion = sesion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	@Override
	public String toString() {
		return "#" + id + " - " + fecha + " - GP[" + (granPremio == null ? "null" : granPremio) + "] TS["
				+ (sesion == null ? "null" : sesion) + "]";
	}

}
