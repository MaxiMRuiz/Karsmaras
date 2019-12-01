package com.races.portal.dto;

/**
 * Definicion del objeto campeonato para el Portal
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class Campeonato {

	private Long id;

	private String nombre;

	private String descripcion;

	private String temporada;

	private String reglamento;

	/**
	 * Constructor por defecto
	 */
	public Campeonato() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param temporada
	 * @param reglamento
	 */
	public Campeonato(Long id, String nombre, String descripcion, String temporada, String reglamento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.temporada = temporada;
		this.reglamento = reglamento;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the temporada
	 */
	public String getTemporada() {
		return temporada;
	}

	/**
	 * @param temporada the temporada to set
	 */
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	/**
	 * @return the reglamento
	 */
	public String getReglamento() {
		return reglamento;
	}

	/**
	 * @param reglamento the reglamento to set
	 */
	public void setReglamento(String reglamento) {
		this.reglamento = reglamento;
	}

}
