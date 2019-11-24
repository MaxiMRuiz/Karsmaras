package com.races.dto;

/**
 * Dto para Puntuaciones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class PuntuacionDto {

	private Long idReglamento;

	private Integer posicion;

	private Integer puntos;

	private Long idTipoSesion;

	/**
	 * Constructor por defecto
	 */
	public PuntuacionDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param idReglamento
	 * @param posicion
	 * @param puntos
	 * @param idTipoSesion
	 */
	public PuntuacionDto(Long idReglamento, Integer posicion, Integer puntos, Long idTipoSesion) {
		super();
		this.idReglamento = idReglamento;
		this.posicion = posicion;
		this.puntos = puntos;
		this.idTipoSesion = idTipoSesion;
	}

	public Long getIdReglamento() {
		return idReglamento;
	}

	public void setIdReglamento(Long idReglamento) {
		this.idReglamento = idReglamento;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Long getIdTipoSesion() {
		return idTipoSesion;
	}

	public void setIdTipoSesion(Long idTipoSesion) {
		this.idTipoSesion = idTipoSesion;
	}

}
