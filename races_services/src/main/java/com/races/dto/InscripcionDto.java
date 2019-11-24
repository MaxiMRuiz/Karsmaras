package com.races.dto;

/**
 * Dto para inscripciones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class InscripcionDto {

	private Long idCampeonato;

	private Long idPiloto;

	private Long idEquipo;

	/**
	 * Constructor por defecto
	 */
	public InscripcionDto() {
		super();
	}

	/**
	 * Contructor por parametros
	 * 
	 * @param idCampeonato
	 * @param idPiloto
	 * @param idEquipo
	 */
	public InscripcionDto(Long idCampeonato, Long idPiloto, Long idEquipo) {
		super();
		this.idCampeonato = idCampeonato;
		this.idPiloto = idPiloto;
		this.idEquipo = idEquipo;
	}

	public Long getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(Long idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public Long getIdPiloto() {
		return idPiloto;
	}

	public void setIdPiloto(Long idPiloto) {
		this.idPiloto = idPiloto;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

}
