package com.races.services;

import java.util.List;

import com.races.dto.PilotoDto;
import com.races.entity.Piloto;

public interface PilotoService {

	Piloto updatePiloto(Long id, PilotoDto pilotoDto);

	Piloto getPiloto(Long id);

	List<Piloto> getAllPilotos(Long id, String nombre, String apellido, String apodo);

	Piloto crearPiloto(PilotoDto pilotoDto);

	boolean borrarPiloto(Long id);
	
	boolean existsPiloto(Long id);
	
}
