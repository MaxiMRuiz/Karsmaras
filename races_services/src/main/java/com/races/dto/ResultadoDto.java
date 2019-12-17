package com.races.dto;

/**
 * Dto para Resultados
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class ResultadoDto {

	private Long idInscripcion;

	private Long idSesion;

	private Integer nVueltas;

	private Integer tiempo;

	/**
	 * Constructor por defecto
	 */
	public ResultadoDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param idPiloto
	 * @param idSesion
	 * @param nVueltas
	 * @param tiempo
	 */
	public ResultadoDto(Long idInscripcion, Long idSesion, Integer nVueltas, Integer tiempo) {
		super();
		this.idInscripcion = idInscripcion;
		this.idSesion = idSesion;
		this.nVueltas = nVueltas;
		this.tiempo = tiempo;
	}

	public Long getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
	}

	public Integer getnVueltas() {
		return nVueltas;
	}

	public void setnVueltas(Integer nVueltas) {
		this.nVueltas = nVueltas;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	public Long getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(Long idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

}
