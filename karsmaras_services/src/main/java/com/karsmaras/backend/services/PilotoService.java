package com.karsmaras.backend.services;

import java.util.List;

import com.karsmaras.backend.dto.PilotoDTO;
import com.karsmaras.backend.entity.Piloto;

public interface PilotoService {

	Piloto updatePiloto(String alias, PilotoDTO pilotoDto);

	List<Piloto> getPiloto(String alias);

	List<Piloto> getAllPilotos();

	Piloto crearPiloto(PilotoDTO pilotoDto);

	boolean borrarPiloto(String alias);
	
}
