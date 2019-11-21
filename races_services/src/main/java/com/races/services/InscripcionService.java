package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.InscripcionDto;
import com.races.entity.Inscripcion;

public interface InscripcionService {

	Inscripcion crearInscripcion(InscripcionDto inscripcionDto) throws RacesException;

	List<Inscripcion> getAllInscripciones(Long idCampeonato, Long idPiloto, Long idEquipo);

	Inscripcion getInscripcion(Long idCampeonato, Long idPiloto, Long idEquipo) throws RacesException;

	boolean borrarInscripcion(InscripcionDto dto) throws RacesException;

	boolean existeInscripcion(InscripcionDto dto);

}
