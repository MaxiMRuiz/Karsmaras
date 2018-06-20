package com.karts.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClasificacionEquipos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Equipo equipo1;
	
	@ManyToOne
	private Equipo equipo2;

	@ManyToOne
	private Equipo equipo3;
	
	@ManyToOne
	private Equipo equipo4;
	
	@ManyToOne
	private Equipo equipo5;
	
	@ManyToOne
	private Equipo equipo6;
	
	@ManyToOne
	private Equipo equipo7;
	
	@ManyToOne
	private Equipo equipo8;
	
	@ManyToOne
	private Equipo equipo9;
	
	@ManyToOne
	private Equipo equipo10;
	
	@ManyToOne
	private Equipo equipo11;
	
	@ManyToOne
	private Equipo equipo12;
	
	@ManyToOne
	private Equipo equipo13;
	
	@ManyToOne
	private Equipo equipo14;
	
	@ManyToOne
	private Equipo equipo15;

	public int getIdClasiEquipos() {
		return id;
	}

	public void setIdClasiEquipos(int idClasiEquipos) {
		this.id = idClasiEquipos;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public Equipo getEquipo3() {
		return equipo3;
	}

	public void setEquipo3(Equipo equipo3) {
		this.equipo3 = equipo3;
	}

	public Equipo getEquipo4() {
		return equipo4;
	}

	public void setEquipo4(Equipo equipo4) {
		this.equipo4 = equipo4;
	}

	public Equipo getEquipo5() {
		return equipo5;
	}

	public void setEquipo5(Equipo equipo5) {
		this.equipo5 = equipo5;
	}

	public Equipo getEquipo6() {
		return equipo6;
	}

	public void setEquipo6(Equipo equipo6) {
		this.equipo6 = equipo6;
	}

	public Equipo getEquipo7() {
		return equipo7;
	}

	public void setEquipo7(Equipo equipo7) {
		this.equipo7 = equipo7;
	}

	public Equipo getEquipo8() {
		return equipo8;
	}

	public void setEquipo8(Equipo equipo8) {
		this.equipo8 = equipo8;
	}

	public Equipo getEquipo9() {
		return equipo9;
	}

	public void setEquipo9(Equipo equipo9) {
		this.equipo9 = equipo9;
	}

	public Equipo getEquipo10() {
		return equipo10;
	}

	public void setEquipo10(Equipo equipo10) {
		this.equipo10 = equipo10;
	}

	public Equipo getEquipo11() {
		return equipo11;
	}

	public void setEquipo11(Equipo equipo11) {
		this.equipo11 = equipo11;
	}

	public Equipo getEquipo12() {
		return equipo12;
	}

	public void setEquipo12(Equipo equipo12) {
		this.equipo12 = equipo12;
	}

	public Equipo getEquipo13() {
		return equipo13;
	}

	public void setEquipo13(Equipo equipo13) {
		this.equipo13 = equipo13;
	}

	public Equipo getEquipo14() {
		return equipo14;
	}

	public void setEquipo14(Equipo equipo14) {
		this.equipo14 = equipo14;
	}

	public Equipo getEquipo15() {
		return equipo15;
	}

	public void setEquipo15(Equipo equipo15) {
		this.equipo15 = equipo15;
	}

	public ClasificacionEquipos() {
		super();
	}
	
}
