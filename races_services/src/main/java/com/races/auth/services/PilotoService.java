package com.races.auth.services;

import java.util.List;

import com.races.auth.entity.Piloto;

public interface PilotoService {

	Piloto updatePiloto(String alias, Piloto pilotoDto);

	List<Piloto> getPiloto(String alias);

	List<Piloto> getAllPilotos();

	Piloto crearPiloto(Piloto pilotoDto);

	boolean borrarPiloto(String alias);
	
}
