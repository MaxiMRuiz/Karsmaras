package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reglamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer nEntrenamientos;
	private Integer nClasificaciones;
	private Integer nCarreras;
	private Integer nPilotos;
	private Integer nEquipos;

	public Reglamento() {
		super();
	}

	public Reglamento(Long id, Integer nEntrenamientos, Integer nClasificaciones, Integer nCarreras, Integer nPilotos,
			Integer nEquipos) {
		super();
		this.id = id;
		this.nEntrenamientos = nEntrenamientos;
		this.nClasificaciones = nClasificaciones;
		this.nCarreras = nCarreras;
		this.nPilotos = nPilotos;
		this.nEquipos = nEquipos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
