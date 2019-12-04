package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Inscripcion;

public interface InscripcionService {

	List<Inscripcion> buscarInscripciones(String idCampeonato, String idPiloto, String idEquipo);

	void crearInscripcion(Inscripcion inscripcion);

	Boolean borrarInscripcion(Long id);

}
