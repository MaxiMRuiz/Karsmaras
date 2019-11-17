package com.races.dto;

public class VueltaDto {

	private Integer tiempo;

	private Integer nVuelta;

	private Long idResultado;

	public VueltaDto() {
		super();
	}

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
