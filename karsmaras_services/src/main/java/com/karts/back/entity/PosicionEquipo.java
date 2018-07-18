package com.karts.back.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class PosicionEquipo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Equipo equipo;
	
	private int posicion;
	
	@ManyToMany(mappedBy = "posicionesEquipo")
    private List<ClasificacionEquipos> clasificacionesEquipo = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public List<ClasificacionEquipos> getClasificacionesEquipo() {
		return clasificacionesEquipo;
	}

	public void setClasificacionesEquipo(List<ClasificacionEquipos> clasificacionesEquipo) {
		this.clasificacionesEquipo = clasificacionesEquipo;
	}

	public PosicionEquipo() {
		super();
	}
	
}
