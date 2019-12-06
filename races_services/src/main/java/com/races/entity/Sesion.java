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
public class Sesion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date fecha;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_gp")
	private GranPremio granPremio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_sesion")
	private TipoSesion tipoSesion;

	/**
	 * Constructor por defecto
	 */
	public Sesion() {
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
	public Sesion(Long id, Date fecha, GranPremio granPremio, TipoSesion tipoSesion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.granPremio = granPremio;
		this.tipoSesion = tipoSesion;
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

	public TipoSesion getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(TipoSesion tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	@Override
	public String toString() {
		return "#" + id + " - " + fecha + " - GP[" + (granPremio == null ? "null" : granPremio) + "] TS["
				+ (tipoSesion == null ? "null" : tipoSesion) + "]";
	}

}
