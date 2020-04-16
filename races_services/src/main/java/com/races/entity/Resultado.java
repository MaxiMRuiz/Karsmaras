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
	@JoinColumn(name = "id_inscripcion")
	private Inscripcion inscripcion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sesion_gp")
	private SesionGP sesionGP;

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
	public Resultado(Long id, Inscripcion inscripcion, SesionGP sesionGp, Integer nVueltas, Integer tiempo) {
		super();
		this.id = id;
		this.inscripcion = inscripcion;
		this.sesionGP = sesionGp;
		this.nVueltas = nVueltas;
		this.tiempo = tiempo;
	}

	public Resultado(Inscripcion inscripcion, SesionGP sesionGp) {
		super();
		this.inscripcion = inscripcion;
		this.sesionGP = sesionGp;
		this.nVueltas = 0;
		this.tiempo = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

	public SesionGP getSesionGP() {
		return sesionGP;
	}

	public void setSesionGP(SesionGP sesionGp) {
		this.sesionGP = sesionGp;
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

	@Override
	public String toString() {
		return "#" + id + " - I[" + (inscripcion == null ? "null" : inscripcion) + "] S["
				+ (sesionGP == null ? "null" : sesionGP) + "] " + nVueltas + "v. " + tiempo + "ms";
	}

}
