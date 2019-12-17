package com.races.portal.dto;

public class Sancion {

	private Long id;
	private Resultado resultado;
	private String descripcion;
	private Integer puntos;
	private String tiempo;

	/**
	 * 
	 */
	public Sancion() {
		super();
	}

	/**
	 * @param id
	 * @param resultado
	 * @param descripcion
	 * @param puntos
	 * @param tiempo
	 */
	public Sancion(Long id, Resultado resultado, String descripcion, Integer puntos, String tiempo) {
		super();
		this.id = id;
		this.resultado = resultado;
		this.descripcion = descripcion;
		this.puntos = puntos;
		this.tiempo = tiempo;
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
	 * @return the resultado
	 */
	public Resultado getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the puntos
	 */
	public Integer getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	/**
	 * @return the tiempo
	 */
	public String getTiempo() {
		return tiempo;
	}

	/**
	 * @param tiempo the tiempo to set
	 */
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

}
