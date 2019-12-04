package com.races.portal.dto;

import com.races.portal.constants.Constants;

import kong.unirest.json.JSONObject;

public class Inscripcion {

	private Long id;
	private Piloto piloto;
	private Campeonato campeonato;
	private Equipo equipo;

	/**
	 * 
	 */
	public Inscripcion() {
		super();
	}

	/**
	 * @param piloto
	 * @param campeonato
	 * @param equipo
	 */
	public Inscripcion(Long id, Piloto piloto, Campeonato campeonato, Equipo equipo) {
		super();
		this.id = id;
		this.piloto = piloto;
		this.campeonato = campeonato;
		this.equipo = equipo;
	}

	/**
	 * @return the piloto
	 */
	public Piloto getPiloto() {
		return piloto;
	}

	/**
	 * @param piloto the piloto to set
	 */
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	/**
	 * @return the campeonato
	 */
	public Campeonato getCampeonato() {
		return campeonato;
	}

	/**
	 * @param campeonato the campeonato to set
	 */
	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	/**
	 * @return the equipo
	 */
	public Equipo getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		JSONObject inscripcion = new JSONObject();
		inscripcion.put(Constants.PARAM_PILOTO, piloto.toString());
		inscripcion.put(Constants.PARAM_EQUIPO, equipo.toString());
		inscripcion.put(Constants.PARAM_CAMPEONATO, campeonato.toString());
		return inscripcion.toString();
	}

}
