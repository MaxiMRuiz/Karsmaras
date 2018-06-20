package com.karts.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClasificacionPilotos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Piloto piloto1;
	
	@ManyToOne
	private Piloto piloto2;

	@ManyToOne
	private Piloto piloto3;
	
	@ManyToOne
	private Piloto piloto4;
	
	@ManyToOne
	private Piloto piloto5;
	
	@ManyToOne
	private Piloto piloto6;
	
	@ManyToOne
	private Piloto piloto7;
	
	@ManyToOne
	private Piloto piloto8;
	
	@ManyToOne
	private Piloto piloto9;
	
	@ManyToOne
	private Piloto piloto10;
	
	@ManyToOne
	private Piloto piloto11;
	
	@ManyToOne
	private Piloto piloto12;
	
	@ManyToOne
	private Piloto piloto13;
	
	@ManyToOne
	private Piloto piloto14;
	
	@ManyToOne
	private Piloto piloto15;

	public int getIdClasiPilotos() {
		return id;
	}

	public void setIdClasiPilotos(int idClasiPilotos) {
		this.id = idClasiPilotos;
	}

	public Piloto getPiloto1() {
		return piloto1;
	}

	public void setPiloto1(Piloto piloto1) {
		this.piloto1 = piloto1;
	}

	public Piloto getPiloto2() {
		return piloto2;
	}

	public void setPiloto2(Piloto piloto2) {
		this.piloto2 = piloto2;
	}

	public Piloto getPiloto3() {
		return piloto3;
	}

	public void setPiloto3(Piloto piloto3) {
		this.piloto3 = piloto3;
	}

	public Piloto getPiloto4() {
		return piloto4;
	}

	public void setPiloto4(Piloto piloto4) {
		this.piloto4 = piloto4;
	}

	public Piloto getPiloto5() {
		return piloto5;
	}

	public void setPiloto5(Piloto piloto5) {
		this.piloto5 = piloto5;
	}

	public Piloto getPiloto6() {
		return piloto6;
	}

	public void setPiloto6(Piloto piloto6) {
		this.piloto6 = piloto6;
	}

	public Piloto getPiloto7() {
		return piloto7;
	}

	public void setPiloto7(Piloto piloto7) {
		this.piloto7 = piloto7;
	}

	public Piloto getPiloto8() {
		return piloto8;
	}

	public void setPiloto8(Piloto piloto8) {
		this.piloto8 = piloto8;
	}

	public Piloto getPiloto9() {
		return piloto9;
	}

	public void setPiloto9(Piloto piloto9) {
		this.piloto9 = piloto9;
	}

	public Piloto getPiloto10() {
		return piloto10;
	}

	public void setPiloto10(Piloto piloto10) {
		this.piloto10 = piloto10;
	}

	public Piloto getPiloto11() {
		return piloto11;
	}

	public void setPiloto11(Piloto piloto11) {
		this.piloto11 = piloto11;
	}

	public Piloto getPiloto12() {
		return piloto12;
	}

	public void setPiloto12(Piloto piloto12) {
		this.piloto12 = piloto12;
	}

	public Piloto getPiloto13() {
		return piloto13;
	}

	public void setPiloto13(Piloto piloto13) {
		this.piloto13 = piloto13;
	}

	public Piloto getPiloto14() {
		return piloto14;
	}

	public void setPiloto14(Piloto piloto14) {
		this.piloto14 = piloto14;
	}

	public Piloto getPiloto15() {
		return piloto15;
	}

	public void setPiloto15(Piloto piloto15) {
		this.piloto15 = piloto15;
	}

	public ClasificacionPilotos() {
		super();
	}
	
}
