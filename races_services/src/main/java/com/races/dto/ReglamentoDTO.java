package com.races.dto;

public class ReglamentoDTO {

	private Integer nEntrenamientos;
	private Integer nClasificaciones;
	private Integer nCarreras;
	private Integer nPilotos;
	private Integer nEquipos;

	public ReglamentoDTO() {
		super();
	}

	public ReglamentoDTO(Integer nEntrenamientos, Integer nClasificaciones, Integer nCarreras, Integer nPilotos,
			Integer nEquipos) {
		super();
		this.nEntrenamientos = nEntrenamientos;
		this.nClasificaciones = nClasificaciones;
		this.nCarreras = nCarreras;
		this.nPilotos = nPilotos;
		this.nEquipos = nEquipos;
	}

	public Integer getnEntrenamientos() {
		return nEntrenamientos;
	}

	public void setnEntrenamientos(Integer nEntrenamientos) {
		this.nEntrenamientos = nEntrenamientos;
	}

	public Integer getnClasificaciones() {
		return nClasificaciones;
	}

	public void setnClasificaciones(Integer nClasificaciones) {
		this.nClasificaciones = nClasificaciones;
	}

	public Integer getnCarreras() {
		return nCarreras;
	}

	public void setnCarreras(Integer nCarreras) {
		this.nCarreras = nCarreras;
	}

	public Integer getnPilotos() {
		return nPilotos;
	}

	public void setnPilotos(Integer nPilotos) {
		this.nPilotos = nPilotos;
	}

	public Integer getnEquipos() {
		return nEquipos;
	}

	public void setnEquipos(Integer nEquipos) {
		this.nEquipos = nEquipos;
	}

}
