package com.races.dto;

/**
 * Dto para Resultados
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class ResultadoDto {

	private Long idPiloto;

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
	public ResultadoDto(Long idPiloto, Long idSesion, Integer nVueltas, Integer tiempo) {
		super();
		this.idPiloto = idPiloto;
		this.idSesion = idSesion;
		this.nVueltas = nVueltas;
		this.tiempo = tiempo;
	}

	public Long getIdPiloto() {
		return idPiloto;
	}

	public void setIdPiloto(Long idPiloto) {
		this.idPiloto = idPiloto;
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

}
