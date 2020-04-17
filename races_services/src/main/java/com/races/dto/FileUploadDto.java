package com.races.dto;

import java.util.ArrayList;
import java.util.List;

public class FileUploadDto {

	private String piloto;
	private List<VueltaDto> tiempos;

	/**
	 * 
	 */
	public FileUploadDto() {
		super();
	}

	/**
	 * @param piloto
	 * @param vuelta
	 * @param tiempo
	 */
	public FileUploadDto(String piloto, List<VueltaDto> tiempos) {
		super();
		this.piloto = piloto;
		if (tiempos == null) {
			this.tiempos = new ArrayList<>();
		} else {
			this.tiempos = new ArrayList<>(tiempos);
		}
	}

	/**
	 * @return the piloto
	 */
	public String getPiloto() {
		return piloto;
	}

	/**
	 * @param piloto the piloto to set
	 */
	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}

	/**
	 * 
	 * @return
	 */
	public List<VueltaDto> getTiempos() {
		if (tiempos == null) {
			return new ArrayList<>();
		} else {
			return new ArrayList<>(tiempos);
		}
	}

	/**
	 * 
	 * @param tiempos
	 */
	public void setTiempos(List<VueltaDto> tiempos) {
		if (tiempos == null) {
			this.tiempos = new ArrayList<>();
		} else {
			this.tiempos = new ArrayList<>(tiempos);
		}
	}

}
