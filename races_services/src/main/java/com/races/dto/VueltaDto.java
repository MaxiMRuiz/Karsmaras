package com.races.dto;

/**
 * Dto para vueltas
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class VueltaDto {

	private Integer tiempo;

	private Integer nVuelta;

	private Long idResultado;

	/**
	 * Constructor por parametros
	 */
	public VueltaDto() {
		super();
	}

	/**
	 * Constructor por parametros
	 * 
	 * @param tiempo
	 * @param nVuelta
	 * @param idResultado
	 */
	public VueltaDto(Integer tiempo, Integer nVuelta, Long idResultado) {
		super();
		this.tiempo = tiempo;
		this.nVuelta = nVuelta;
		this.idResultado = idResultado;
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

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

}
