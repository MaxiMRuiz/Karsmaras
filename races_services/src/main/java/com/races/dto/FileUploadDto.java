package com.races.dto;

public class FileUploadDto {

	private String piloto;
	private Integer vuelta;
	private String tiempo;

	/**
	 * 
	 */
	public FileUploadDto() {
		super();
	}

	/**
	 * @param piloto
	 * @param vuelta
	 * @param tiempo
	 */
	public FileUploadDto(String piloto, Integer vuelta, String tiempo) {
		super();
		this.piloto = piloto;
		this.vuelta = vuelta;
		this.tiempo = tiempo;
	}

	/**
	 * @return the piloto
	 */
	public String getPiloto() {
		return piloto;
	}

	/**
	 * @param piloto the piloto to set
	 */
	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}

	/**
	 * @return the vuelta
	 */
	public Integer getVuelta() {
		return vuelta;
	}

	/**
	 * @param vuelta the vuelta to set
	 */
	public void setVuelta(Integer vuelta) {
		this.vuelta = vuelta;
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
