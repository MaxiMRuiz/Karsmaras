package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Puntuacion;

public interface PuntuacionService {

	List<Puntuacion> buscarPuntuaciones(String id);

	Puntuacion buscarPuntuacion(String id, String subId);

	Boolean editarPuntuacion(Puntuacion puntuacion);

	Boolean crearPuntuacion(Puntuacion puntuacion);

	Boolean borrarPuntuacion(String id, String obj);

}
