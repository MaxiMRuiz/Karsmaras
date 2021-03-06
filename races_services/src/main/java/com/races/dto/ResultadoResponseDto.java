package com.races.dto;

import com.races.entity.Inscripcion;
import com.races.entity.Resultado;
import com.races.entity.SesionGP;
import com.races.entity.Vuelta;

public class ResultadoResponseDto implements Comparable<ResultadoResponseDto> {

	private Long id;
	private Inscripcion inscripcion;
	private SesionGP sesionGp;
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
	public ResultadoResponseDto(Long id, Inscripcion inscripcion, SesionGP sesionGp, Integer nVueltas, Integer tiempo,
			Integer vRapida) {
		super();
		this.id = id;
		this.inscripcion = inscripcion;
		this.sesionGp = sesionGp;
		this.nVueltas = nVueltas;
		this.tiempo = tiempo;
		this.vRapida = vRapida;
	}

	/**
	 * Constructor por Resultado y Vuelta
	 * 
	 * @param resultado
	 * @param vRapida
	 */
	public ResultadoResponseDto(Resultado resultado, Vuelta vRapida) {
		this.id = resultado.getId();
		this.inscripcion = resultado.getInscripcion();
		this.sesionGp = resultado.getSesionGP();
		this.nVueltas = resultado.getnVueltas();
		this.tiempo = resultado.getTiempo();
		this.vRapida = vRapida == null ? null : vRapida.getTiempo();
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
	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	/**
	 * @param piloto the piloto to set
	 */
	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

	/**
	 * @return the sesion
	 */
	public SesionGP getSesionGP() {
		return sesionGp;
	}

	/**
	 * @param sesion the sesion to set
	 */
	public void setSesionGP(SesionGP sesionGp) {
		this.sesionGp = sesionGp;
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

	/**
	 * Metodo de ordenacion para Sort
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(ResultadoResponseDto resultado2) {

		if (vRapida != null && resultado2.getvRapida() != null && vRapida < resultado2.getvRapida()) {
			return -1;
		}
		if (vRapida != null && resultado2.getvRapida() != null && vRapida > resultado2.getvRapida()) {
			return 1;
		}
		return 0;
	}

}
