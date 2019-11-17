package com.races.services;

import java.util.List;

import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;

public interface PuntuacionService {

	Puntuacion crearPuntuacion(PuntuacionDto puntuacionDto);

	List<Puntuacion> getAllPuntuaciones(Long idReglamento, Integer posicion, Integer puntos, Long idTipoSesion);

	Puntuacion getPuntuacion(Long id);

	Puntuacion updatePuntuacion(Long id, PuntuacionDto puntuacionDto);

	boolean borrarPuntuacion(Long id);
	
	boolean existsPuntuacion(Long id);
	
}
