package com.races.dto;

import com.races.entity.Piloto;
import com.races.entity.Resultado;
import com.races.entity.Sesion;
import com.races.entity.Vuelta;

public class ResultadoResponseDto {

	private Long id;
	private Piloto piloto;
	private Sesion sesion;
	private Integer nVueltas;
	private Integer tiempo;
	private Integer vRapida;

	/**
	 * 
	 */
	public ResultadoResponseDto() {
		super();
	}

	/**
	 * @param id
	 * @param piloto
	 * @param sesion
	 * @param nVueltas
	 * @param tiempo
	 * @param vRapida
	 */
	public ResultadoResponseDto(Long id, Piloto piloto, Sesion sesion, Integer nVueltas, Integer tiempo,
			Integer vRapida) {
		super();
		this.id = id;
		this.piloto = piloto;
		this.sesion = sesion;
		this.nVueltas = nVueltas;
		this.tiempo = tiempo;
		this.vRapida = vRapida;
	}

	/**
	 * Constructor por Resultado y Vuelta
	 * @param resultado
	 * @param vRapida
	 */
	public ResultadoResponseDto(Resultado resultado, Vuelta vRapida) {
		this.id = resultado.getId();
		this.piloto = resultado.getPiloto();
		this.sesion = resultado.getSesion();
		this.nVueltas = resultado.getnVueltas();
		this.tiempo = resultado.getTiempo();
		this.vRapida = vRapida.getTiempo();
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
	 * @return the sesion
	 */
	public Sesion getSesion() {
		return sesion;
	}

	/**
	 * @param sesion the sesion to set
	 */
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	/**
	 * @return the nVueltas
	 */
	public Integer getnVueltas() {
		return nVueltas;
	}

	/**
	 * @param nVueltas the nVueltas to set
	 */
	public void setnVueltas(Integer nVueltas) {
		this.nVueltas = nVueltas;
	}

	/**
	 * @return the tiempo
	 */
	public Integer getTiempo() {
		return tiempo;
	}

	/**
	 * @param tiempo the tiempo to set
	 */
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * @return the vRapida
	 */
	public Integer getvRapida() {
		return vRapida;
	}

	/**
	 * @param vRapida the vRapida to set
	 */
	public void setvRapida(Integer vRapida) {
		this.vRapida = vRapida;
	}

}
