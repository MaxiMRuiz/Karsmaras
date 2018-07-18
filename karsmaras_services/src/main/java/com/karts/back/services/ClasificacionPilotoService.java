package com.karts.back.services;

import java.util.List;

import com.karts.back.dto.ClasificacionPilotosDTO;
import com.karts.back.entity.ClasificacionPilotos;

public interface ClasificacionPilotoService {

	ClasificacionPilotos crearClasificacionPilotos(ClasificacionPilotosDTO clasiPilotosDto);

	List<ClasificacionPilotos> getAllClasificacionPilotos();

	List<ClasificacionPilotos> getClasificacionPilotos(Integer id);

	ClasificacionPilotos updateClasificacionPilotos(Integer id, ClasificacionPilotosDTO equipoDto);

	void borrarClasificacionPilotos(Integer id);
	
}
