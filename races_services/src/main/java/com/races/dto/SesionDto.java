package com.races.dto;

/**
 * Dto para Puntuaciones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class SesionDto {

	private Long idReglamento;

	private String descripcion;

	private Long idTipoSesion;

	/**
	 * Constructor por defecto
	 */
	public SesionDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param idReglamento
	 * @param posicion
	 * @param puntos
	 * @param idTipoSesion
	 */
	public SesionDto(Long idReglamento, String descripcion, Long idTipoSesion) {
		super();
		this.idReglamento = idReglamento;
		this.descripcion = descripcion;
		this.idTipoSesion = idTipoSesion;
	}

	public Long getIdReglamento() {
		return idReglamento;
	}

	public void setIdReglamento(Long idReglamento) {
		this.idReglamento = idReglamento;
	}

	public Long getIdTipoSesion() {
		return idTipoSesion;
	}

	public void setIdTipoSesion(Long idTipoSesion) {
		this.idTipoSesion = idTipoSesion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
