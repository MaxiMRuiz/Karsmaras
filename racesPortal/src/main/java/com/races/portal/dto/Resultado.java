package com.races.portal.dto;

public class Resultado {

	private Long id;
	private Piloto piloto;
	private Sesion sesion;
	private Integer tiempo;
	private Integer vueltas;

	/**
	 * 
	 */
	public Resultado() {
		super();
	}

	/**
	 * @param id
	 * @param piloto
	 * @param sesion
	 * @param tiempo
	 * @param vueltas
	 */
	public Resultado(Long id, Piloto piloto, Sesion sesion, Integer tiempo, Integer vueltas) {
		super();
		this.id = id;
		this.piloto = piloto;
		this.sesion = sesion;
		this.tiempo = tiempo;
		this.vueltas = vueltas;
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
	 * @return the vueltas
	 */
	public Integer getVueltas() {
		return vueltas;
	}

	/**
	 * @param vueltas the vueltas to set
	 */
	public void setVueltas(Integer vueltas) {
		this.vueltas = vueltas;
	}

	public String getTiempoString() {
		if (tiempo == null || tiempo == 0) {
			return "0:0:0.000";
		} else {
			return (int) Math.floor(((tiempo / 1000) / 60) / 60) + ":" + (int) Math.floor(((tiempo / 1000) / 60) % 60)
					+ ":" + (int) Math.floor((tiempo / 1000) % 60) + "." + tiempo % 1000;
		}
	}

}
