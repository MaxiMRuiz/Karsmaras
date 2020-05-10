package com.races.dto;

/**
 * Dto para Sanciones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class SancionDto {

	private Long idResultado;

	private String descripcion;

	private Integer puntos;

	private Integer tiempo;

	/**
	 * Constructor por defecto
	 */
	public SancionDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param idResultado
	 * @param descripcion
	 * @param puntos
	 * @param tiempo
	 */
	public SancionDto(Long idResultado, String descripcion, Integer puntos, Integer tiempo) {
		super();
		this.idResultado = idResultado;
		this.descripcion = descripcion;
		this.puntos = puntos;
		this.tiempo = tiempo;
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

}
