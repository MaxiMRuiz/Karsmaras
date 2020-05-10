package com.races.entity;

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

	private String descripcion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_reglamento")
	private Reglamento reglamento;

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
	 * @param id
	 * @param descripcion
	 * @param reglamento
	 * @param tipoSesion
	 */
	public Sesion(Long id, String descripcion, Reglamento reglamento, TipoSesion tipoSesion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.reglamento = reglamento;
		this.tipoSesion = tipoSesion;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the reglamento
	 */
	public Reglamento getReglamento() {
		return reglamento;
	}

	/**
	 * @param reglamento the reglamento to set
	 */
	public void setReglamento(Reglamento reglamento) {
		this.reglamento = reglamento;
	}

	/**
	 * @return the tipoSesion
	 */
	public TipoSesion getTipoSesion() {
		return tipoSesion;
	}

	/**
	 * @param tipoSesion the tipoSesion to set
	 */
	public void setTipoSesion(TipoSesion tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	@Override
	public String toString() {
		return "#" + id + " - " + descripcion + " - Reglamento[" + (reglamento == null ? "null" : reglamento) + "] TS["
				+ (tipoSesion == null ? "null" : tipoSesion) + "]";
	}

}
