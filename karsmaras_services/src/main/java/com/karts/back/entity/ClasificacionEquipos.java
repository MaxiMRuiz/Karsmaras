package com.karts.back.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ClasificacionEquipos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "clasi_equipos_posiciones", joinColumns = {
			@JoinColumn(name = "clasificacion_equipos_id") }, inverseJoinColumns = { @JoinColumn(name = "posicion_equipo_id") })
	private List<PosicionEquipo> posicionesEquipo = new ArrayList<>();

	public int getIdClasiEquipos() {
		return id;
	}

	public void setIdClasiEquipos(int idClasiEquipos) {
		this.id = idClasiEquipos;
	}

	public ClasificacionEquipos() {
		super();
	}
	
}
