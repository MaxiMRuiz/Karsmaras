package com.races.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vuelta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer tiempo;

	private Integer nVuelta;

	@ManyToOne
	private Resultado resultado;

	public Vuelta() {
		super();
	}

	public Vuelta(Long id, Integer tiempo, Integer nVuelta, Resultado resultado) {
		super();
		this.id = id;
		this.tiempo = tiempo;
		this.nVuelta = nVuelta;
		this.resultado = resultado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	public Integer getnVuelta() {
		return nVuelta;
	}

	public void setnVuelta(Integer nVuelta) {
		this.nVuelta = nVuelta;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

}
