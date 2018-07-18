package com.karsmaras.backend.dto;

import java.util.List;

public class ReglamentoDTO {

	private List<PuntosPosicionDTO> puntosPosiciones;
	private int puntosVR;
	private int puntosPole;
	private String normativa;
	private List<SancionesDTO> sanciones;

	public List<PuntosPosicionDTO> getPuntosPosiciones() {
		return puntosPosiciones;
	}

	public void setPuntosPosiciones(List<PuntosPosicionDTO> puntosPosiciones) {
		this.puntosPosiciones = puntosPosiciones;
	}

	public int getPuntosVR() {
		return puntosVR;
	}

	public void setPuntosVR(int puntosVR) {
		this.puntosVR = puntosVR;
	}

	public int getPuntosPole() {
		return puntosPole;
	}

	public void setPuntosPole(int puntosPole) {
		this.puntosPole = puntosPole;
	}

	public String getNormativa() {
		return normativa;
	}

	public void setNormativa(String normativa) {
		this.normativa = normativa;
	}

	public List<SancionesDTO> getSanciones() {
		return sanciones;
	}

	public void setSanciones(List<SancionesDTO> sanciones) {
		this.sanciones = sanciones;
	}

	public ReglamentoDTO(List<PuntosPosicionDTO> puntosPosiciones, int puntosVR, int puntosPole, String normativa,
			List<SancionesDTO> sanciones) {
		super();
		this.puntosPosiciones = puntosPosiciones;
		this.puntosVR = puntosVR;
		this.puntosPole = puntosPole;
		this.normativa = normativa;
		this.sanciones = sanciones;
	}

	public ReglamentoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
