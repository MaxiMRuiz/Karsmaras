package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidad de la tabla Gran Premio
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Entity
public class GranPremio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_campeonato")
	private Campeonato campeonato;

	private String ubicacion;

	/**
	 * Constructor por defecto
	 */
	public GranPremio() {
		super();
	}

	/**
	 * Constructor por parametros
	 * @param id
	 * @param campeonato
	 * @param ubicacion
	 */
	public GranPremio(Long id, Campeonato campeonato, String ubicacion) {
		super();
		this.id = id;
		this.campeonato = campeonato;
		this.ubicacion = ubicacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
