package com.karts.back.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "clasificacion")
public class Clasificacion {

	@Id
	@Column(name = "idClasificacion", nullable = false)
	private int idClasificacion;
	
	@Column(name = "pos1", nullable = true)
	private int pos1;
	
	@Column(name = "pos2", nullable = true)
	private int pos2;

	@Column(name = "pos3", nullable = true)
	private int pos3;
	
	@Column(name = "pos4", nullable = true)
	private int pos4;
	
	@Column(name = "pos5", nullable = true)
	private int pos5;
	
	@Column(name = "pos6", nullable = true)
	private int pos6;
	
	@Column(name = "pos7", nullable = true)
	private int pos7;
	
	@Column(name = "pos8", nullable = true)
	private int pos8;
	
	@Column(name = "pos9", nullable = true)
	private int pos9;
	
	@Column(name = "pos10", nullable = true)
	private int pos10;
	
	@Column(name = "pos11", nullable = true)
	private int pos11;
	
	@Column(name = "pos12", nullable = true)
	private int pos12;
	
	@Column(name = "pos13", nullable = true)
	private int pos13;
	
	@Column(name = "pos14", nullable = true)
	private int pos14;
	
	@Column(name = "pos14", nullable = true)
	private int pos15;

	public int getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(int idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public int getPos1() {
		return pos1;
	}

	public void setPos1(int pos1) {
		this.pos1 = pos1;
	}

	public int getPos2() {
		return pos2;
	}

	public void setPos2(int pos2) {
		this.pos2 = pos2;
	}

	public int getPos3() {
		return pos3;
	}

	public void setPos3(int pos3) {
		this.pos3 = pos3;
	}

	public int getPos4() {
		return pos4;
	}

	public void setPos4(int pos4) {
		this.pos4 = pos4;
	}

	public int getPos5() {
		return pos5;
	}

	public void setPos5(int pos5) {
		this.pos5 = pos5;
	}

	public int getPos6() {
		return pos6;
	}

	public void setPos6(int pos6) {
		this.pos6 = pos6;
	}

	public int getPos7() {
		return pos7;
	}

	public void setPos7(int pos7) {
		this.pos7 = pos7;
	}

	public int getPos8() {
		return pos8;
	}

	public void setPos8(int pos8) {
		this.pos8 = pos8;
	}

	public int getPos9() {
		return pos9;
	}

	public void setPos9(int pos9) {
		this.pos9 = pos9;
	}

	public int getPos10() {
		return pos10;
	}

	public void setPos10(int pos10) {
		this.pos10 = pos10;
	}

	public int getPos11() {
		return pos11;
	}

	public void setPos11(int pos11) {
		this.pos11 = pos11;
	}

	public int getPos12() {
		return pos12;
	}

	public void setPos12(int pos12) {
		this.pos12 = pos12;
	}

	public int getPos13() {
		return pos13;
	}

	public void setPos13(int pos13) {
		this.pos13 = pos13;
	}

	public int getPos14() {
		return pos14;
	}

	public void setPos14(int pos14) {
		this.pos14 = pos14;
	}

	public int getPos15() {
		return pos15;
	}

	public void setPos15(int pos15) {
		this.pos15 = pos15;
	}

	public Clasificacion() {
		super();
	}
	
	
}
