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
public class ClasificacionPilotos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "clasi_pilotos_posiciones", joinColumns = {
			@JoinColumn(name = "clasificacion_pilotos_id") }, inverseJoinColumns = { @JoinColumn(name = "posicion_piloto_id") })
	private List<PosicionPiloto> posicionesPiloto = new ArrayList<>();

	public int getIdClasiPilotos() {
		return id;
	}

	public void setIdClasiPilotos(int idClasiPilotos) {
		this.id = idClasiPilotos;
	}

	public List<PosicionPiloto> getPosicionesPiloto() {
		return posicionesPiloto;
	}

	public void setPosicionesPiloto(List<PosicionPiloto> posicionesPiloto) {
		this.posicionesPiloto = posicionesPiloto;
	}
	
	public ClasificacionPilotos() {
		super();
	}
}
