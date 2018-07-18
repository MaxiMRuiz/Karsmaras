package com.karsmaras.backend.services;

import java.util.List;

import com.karsmaras.backend.dto.ClasificacionPilotosDTO;
import com.karsmaras.backend.entity.ClasificacionPilotos;

public interface ClasificacionPilotoService {

	ClasificacionPilotos crearClasificacionPilotos(ClasificacionPilotosDTO clasiPilotosDto);

	List<ClasificacionPilotos> getAllClasificacionPilotos();

	List<ClasificacionPilotos> getClasificacionPilotos(Integer id);

	ClasificacionPilotos updateClasificacionPilotos(Integer id, ClasificacionPilotosDTO equipoDto);

	void borrarClasificacionPilotos(Integer id);
	
}
