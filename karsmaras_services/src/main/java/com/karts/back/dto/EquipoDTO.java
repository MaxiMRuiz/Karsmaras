package com.karts.back.dto;

public class EquipoDTO {

	private String alias;
	
	private String descripcion;
	
	private String piloto1;
	
	private String piloto2;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPiloto1() {
		return piloto1;
	}

	public void setPiloto1(String piloto1) {
		this.piloto1 = piloto1;
	}

	public String getPiloto2() {
		return piloto2;
	}

	public void setPiloto2(String piloto2) {
		this.piloto2 = piloto2;
	}

	public EquipoDTO() {
		super();
	}

	public EquipoDTO(String alias, String descripcion, String piloto1, String piloto2) {
		super();
		this.alias = alias;
		this.descripcion = descripcion;
		this.piloto1 = piloto1;
		this.piloto2 = piloto2;
	}

	@Override
	public String toString() {
		return "EquipoDTO [alias=" + alias + ", descripcion=" + descripcion + ", piloto1=" + piloto1 + ", piloto2="
				+ piloto2 + "]";
	}
	
}
