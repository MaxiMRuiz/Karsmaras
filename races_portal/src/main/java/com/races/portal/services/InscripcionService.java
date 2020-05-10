package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Inscripcion;

public interface InscripcionService {

	List<Inscripcion> buscarInscripciones(String idCampeonato, String idPiloto, String idEquipo, String jwt, String user);

	void crearInscripcion(Inscripcion inscripcion, String jwt, String user);

	Boolean borrarInscripcion(Long id, String jwt, String user);

}
