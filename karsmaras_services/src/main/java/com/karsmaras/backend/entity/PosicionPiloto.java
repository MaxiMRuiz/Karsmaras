package com.karsmaras.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class PosicionPiloto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Piloto piloto;
	
	private int posicion;
	
	@ManyToMany(mappedBy = "posicionesPiloto")
    private List<ClasificacionPilotos> clasificacionesPiloto = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public List<ClasificacionPilotos> getClasificacionesPiloto() {
		return clasificacionesPiloto;
	}

	public void setClasificacionesPiloto(List<ClasificacionPilotos> clasificacionesPiloto) {
		this.clasificacionesPiloto = clasificacionesPiloto;
	}

	public PosicionPiloto() {
		super();
	}	
	
}
