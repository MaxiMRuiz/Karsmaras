package com.karts.back.services;

import java.util.List;

import com.karts.back.dto.PilotoDTO;
import com.karts.back.entity.Piloto;

public interface PilotoService {

	Piloto updatePiloto(String alias, PilotoDTO pilotoDto);

	List<Piloto> getPiloto(String alias);

	List<Piloto> getAllPilotos();

	Piloto crearPiloto(PilotoDTO pilotoDto);

	boolean borrarPiloto(String alias);
	
}
