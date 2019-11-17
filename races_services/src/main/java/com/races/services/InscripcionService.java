package com.races.services;

import java.util.List;

import com.races.dto.InscripcionDto;
import com.races.entity.Inscripcion;


public interface InscripcionService {

	Inscripcion crearInscripcion(InscripcionDto inscripcionDto);

	List<Inscripcion> getAllInscripciones(Long idCampeonato, Long idPiloto, Long idEquipo);

	Inscripcion getInscripcion(Long idCampeonato, Long idPiloto, Long idEquipo);

	boolean borrarInscripcion(InscripcionDto dto);
	
	boolean existeInscripcion(InscripcionDto dto);
		
}
