package com.races.portal.dto;

public class Resultado {

	private Long id;
	private Inscripcion inscripcion;
	private Sesion sesion;
	private Integer tiempo;
	private Integer vueltas;
	private Integer vRapida;

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
	public Resultado(Long id, Inscripcion inscripcion, Sesion sesion, Integer tiempo, Integer vueltas, Integer vRapida) {
		super();
		this.id = id;
		this.inscripcion = inscripcion;
		this.sesion = sesion;
		this.tiempo = tiempo;
		this.vueltas = vueltas;
		this.vRapida = vRapida;
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
			return "0:0.000";
		} else {
			return horas(tiempo) + min(tiempo) + seg(tiempo) + miliseg(tiempo);
		}
	}

	public Integer getvRapida() {
		return vRapida;
	}

	public void setvRapida(Integer vRapida) {
		this.vRapida = vRapida;
	}

	public String getVueltaRapida() {
		if (vRapida == null || vRapida == 0) {
			return "0:0.000";
		} else {
			return horas(vRapida) + min(vRapida) + seg(vRapida) + miliseg(vRapida);
		}
	}

	private String horas(Integer tiempo) {
		Integer horas = (tiempo > 3599999 ? Integer.valueOf(tiempo / 3600000) : 0);
		if (horas > 9) {
			return horas + ":";
		} else if (horas > 0) {
			return "0" + horas + ":";
		} else {
			return "";
		}
	}

	private String min(Integer tiempo) {
		Integer min = (tiempo > 59999 ? Integer.valueOf((tiempo / 60000) % 60) : 0);
		if (min > 9) {
			return min + ":";
		} else {
			return "0" + min + ":";
		}
	}

	private String seg(Integer tiempo) {
		Integer seg = Integer.valueOf((tiempo / 1000) % 60);
		if (seg > 9) {
			return seg + ".";
		} else {
			return "0" + seg + ".";
		}
	}

	private String miliseg(Integer tiempo) {
		Integer miliseg = tiempo % 1000;
		if (miliseg > 99) {
			return "" + miliseg;
		} else if (miliseg > 9) {
			return "0" + miliseg;
		} else {
			return "00" + miliseg;
		}
	}

}
