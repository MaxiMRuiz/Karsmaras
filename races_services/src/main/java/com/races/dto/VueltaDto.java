package com.races.dto;

/**
 * Dto para vueltas
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class VueltaDto {

	private String tiempo;

	private Integer vuelta;

	/**
	 * 
	 */
	public VueltaDto() {
		super();
	}

	/**
	 * @param tiempo
	 * @param vuelta
	 */
	public VueltaDto(String tiempo, Integer vuelta) {
		super();
		this.tiempo = tiempo;
		this.vuelta = vuelta;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public Integer getVuelta() {
		return vuelta;
	}

	public void setVuelta(Integer vuelta) {
		this.vuelta = vuelta;
	}

}
