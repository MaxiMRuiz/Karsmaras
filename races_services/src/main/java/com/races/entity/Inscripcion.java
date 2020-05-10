package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidad de la tabla Inscripcion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Entity
public class Inscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_campeonato")
	private Campeonato campeonato;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_piloto")
	private Piloto piloto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_equipo")
	private Equipo equipo;

	/**
	 * Constructor por defecto
	 */
	public Inscripcion() {
		super();
	}

	/**
	 * Constructor por parametros sin id
	 * 
	 * @param campeonato
	 * @param piloto
	 * @param equipo
	 */
	public Inscripcion(Campeonato campeonato, Piloto piloto, Equipo equipo) {
		super();
		this.campeonato = campeonato;
		this.piloto = piloto;
		this.equipo = equipo;
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param campeonato
	 * @param piloto
	 * @param equipo
	 */
	public Inscripcion(Long id, Campeonato campeonato, Piloto piloto, Equipo equipo) {
		super();
		this.id = id;
		this.campeonato = campeonato;
		this.piloto = piloto;
		this.equipo = equipo;
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

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return "#" + id + " - C[" + (campeonato == null ? "null" : campeonato) + "] - P["
				+ (piloto == null ? "null" : piloto) + "] - E[" + (equipo == null ? "null" : equipo) + "]";
	}

}
