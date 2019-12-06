package com.races.portal.dto;

public class Vuelta {

	private Long id;
	private Integer tiempo;
	private Integer nVuelta;
	private Resultado resultado;

	/**
	 * 
	 */
	public Vuelta() {
		super();
	}

	/**
	 * @param id
	 * @param tiempo
	 * @param nVuelta
	 * @param resultado
	 */
	public Vuelta(Long id, Integer tiempo, Integer nVuelta, Resultado resultado) {
		super();
		this.id = id;
		this.tiempo = tiempo;
		this.nVuelta = nVuelta;
		this.resultado = resultado;
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
	 * @return the nVuelta
	 */
	public Integer getnVuelta() {
		return nVuelta;
	}

	/**
	 * @param nVuelta the nVuelta to set
	 */
	public void setnVuelta(Integer nVuelta) {
		this.nVuelta = nVuelta;
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

	public String getTiempoFormat() {
		if (tiempo == null || tiempo == 0) {
			return "0:0.000";
		} else {
			return horas(tiempo) + min(tiempo) + seg(tiempo) + miliseg(tiempo);
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
