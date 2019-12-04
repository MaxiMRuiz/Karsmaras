package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidad de la tabla Resultado
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Entity
public class Resultado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_piloto")
	private Piloto piloto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sesion")
	private Sesion sesion;

	private Integer nVueltas;

	private Integer tiempo;

	/**
	 * Constructor por defecto
	 */
	public Resultado() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param piloto
	 * @param sesion
	 * @param nVueltas
	 * @param tiempo
	 */
	public Resultado(Long id, Piloto piloto, Sesion sesion, Integer nVueltas, Integer tiempo) {
		super();
		this.id = id;
		this.piloto = piloto;
		this.sesion = sesion;
		this.nVueltas = nVueltas;
		this.tiempo = tiempo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public Integer getnVueltas() {
		return nVueltas;
	}

	public void setnVueltas(Integer nVueltas) {
		this.nVueltas = nVueltas;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

}
