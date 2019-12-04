package com.races.dto;

/**
 * Dto para Reglamentos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class ReglamentoDto {

	private String descripcion;
	private Integer nEntrenamientos;
	private Integer nClasificaciones;
	private Integer nCarreras;
	private Integer nPilotos;
	private Integer nEquipos;

	/**
	 * Constructor por defecto
	 */
	public ReglamentoDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param nEntrenamientos
	 * @param nClasificaciones
	 * @param nCarreras
	 * @param nPilotos
	 * @param nEquipos
	 */
	public ReglamentoDto(String descripcion, Integer nEntrenamientos, Integer nClasificaciones, Integer nCarreras,
			Integer nPilotos, Integer nEquipos) {
		super();
		this.descripcion = descripcion;
		this.nEntrenamientos = nEntrenamientos;
		this.nClasificaciones = nClasificaciones;
		this.nCarreras = nCarreras;
		this.nPilotos = nPilotos;
		this.nEquipos = nEquipos;
	}

	public Integer getnEntrenamientos() {
		return nEntrenamientos;
	}

	public void setnEntrenamientos(Integer nEntrenamientos) {
		this.nEntrenamientos = nEntrenamientos;
	}

	public Integer getnClasificaciones() {
		return nClasificaciones;
	}

	public void setnClasificaciones(Integer nClasificaciones) {
		this.nClasificaciones = nClasificaciones;
	}

	public Integer getnCarreras() {
		return nCarreras;
	}

	public void setnCarreras(Integer nCarreras) {
		this.nCarreras = nCarreras;
	}

	public Integer getnPilotos() {
		return nPilotos;
	}

	public void setnPilotos(Integer nPilotos) {
		this.nPilotos = nPilotos;
	}

	public Integer getnEquipos() {
		return nEquipos;
	}

	public void setnEquipos(Integer nEquipos) {
		this.nEquipos = nEquipos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
