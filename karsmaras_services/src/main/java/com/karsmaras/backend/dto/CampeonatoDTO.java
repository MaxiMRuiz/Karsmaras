package com.karsmaras.backend.dto;

import com.karsmaras.backend.entity.ClasificacionEquipos;
import com.karsmaras.backend.entity.ClasificacionPilotos;
import com.karsmaras.backend.entity.Reglamento;

public class CampeonatoDTO {
	
	private String nombreCampeonato;
	
	private Reglamento reglamento;

	private ClasificacionPilotos clasificacionPilotos;
	
	private ClasificacionEquipos clasificacionEquipos;
	
	private int year;

	public String getNombreCampeonato() {
		return nombreCampeonato;
	}

	public void setNombreCampeonato(String nombreCampeonato) {
		this.nombreCampeonato = nombreCampeonato;
	}

	public Reglamento getReglamento() {
		return reglamento;
	}

	public void setReglamento(Reglamento reglamento) {
		this.reglamento = reglamento;
	}

	public ClasificacionPilotos getClasificacionPilotos() {
		return clasificacionPilotos;
	}

	public void setClasificacionPilotos(ClasificacionPilotos clasificacionPilotos) {
		this.clasificacionPilotos = clasificacionPilotos;
	}

	public ClasificacionEquipos getClasificacionEquipos() {
		return clasificacionEquipos;
	}

	public void setClasificacionEquipos(ClasificacionEquipos clasificacionEquipos) {
		this.clasificacionEquipos = clasificacionEquipos;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public CampeonatoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CampeonatoDTO(String nombreCampeonato, Reglamento reglamento, ClasificacionPilotos clasificacionPilotos,
			ClasificacionEquipos clasificacionEquipos, int year) {
		super();
		this.nombreCampeonato = nombreCampeonato;
		this.reglamento = reglamento;
		this.clasificacionPilotos = clasificacionPilotos;
		this.clasificacionEquipos = clasificacionEquipos;
		this.year = year;
	}

	@Override
	public String toString() {
		return "CampeonatoDTO [nombreCampeonato=" + nombreCampeonato + ", reglamento=" + reglamento
				+ ", clasificacionPilotos=" + clasificacionPilotos + ", clasificacionEquipos=" + clasificacionEquipos
				+ ", year=" + year + "]";
	}
	
}
