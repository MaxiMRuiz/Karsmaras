package com.races.portal.dto;

/**
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class Reglamento {
	private Long id;
	private Long nEntrenamientos;
	private Long nClasificaciones;
	private Long nCarreras;
	private Long nPilotos;
	private Long nEquipos;
	/**
	 * 
	 */
	public Reglamento() {
		super();
	}
	/**
	 * @param id
	 * @param nEntrenamientos
	 * @param nClasificaciones
	 * @param nCarreras
	 * @param nPilotos
	 * @param nEquipos
	 */
	public Reglamento(Long id, Long nEntrenamientos, Long nClasificaciones, Long nCarreras, Long nPilotos,
			Long nEquipos) {
		super();
		this.id = id;
		this.nEntrenamientos = nEntrenamientos;
		this.nClasificaciones = nClasificaciones;
		this.nCarreras = nCarreras;
		this.nPilotos = nPilotos;
		this.nEquipos = nEquipos;
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
	 * @return the nEntrenamientos
	 */
	public Long getnEntrenamientos() {
		return nEntrenamientos;
	}
	/**
	 * @param nEntrenamientos the nEntrenamientos to set
	 */
	public void setnEntrenamientos(Long nEntrenamientos) {
		this.nEntrenamientos = nEntrenamientos;
	}
	/**
	 * @return the nClasificaciones
	 */
	public Long getnClasificaciones() {
		return nClasificaciones;
	}
	/**
	 * @param nClasificaciones the nClasificaciones to set
	 */
	public void setnClasificaciones(Long nClasificaciones) {
		this.nClasificaciones = nClasificaciones;
	}
	/**
	 * @return the nCarreras
	 */
	public Long getnCarreras() {
		return nCarreras;
	}
	/**
	 * @param nCarreras the nCarreras to set
	 */
	public void setnCarreras(Long nCarreras) {
		this.nCarreras = nCarreras;
	}
	/**
	 * @return the nPilotos
	 */
	public Long getnPilotos() {
		return nPilotos;
	}
	/**
	 * @param nPilotos the nPilotos to set
	 */
	public void setnPilotos(Long nPilotos) {
		this.nPilotos = nPilotos;
	}
	/**
	 * @return the nEquipos
	 */
	public Long getnEquipos() {
		return nEquipos;
	}
	/**
	 * @param nEquipos the nEquipos to set
	 */
	public void setnEquipos(Long nEquipos) {
		this.nEquipos = nEquipos;
	}
	
}
