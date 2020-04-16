package com.races.dto;

/**
 * Dto para Puntuaciones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class PuntuacionDto {

	private Long idSesion;

	private Integer posicion;

	private Integer puntos;

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
	public PuntuacionDto(Long idSesion, Integer posicion, Integer puntos, Long idTipoSesion) {
		super();
		this.idSesion = idSesion;
		this.posicion = posicion;
		this.puntos = puntos;
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

	public Long getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
	}

}
