package com.races.portal.services;

import java.io.File;
import java.util.List;

import com.races.portal.dto.Puntuacion;

public interface PuntuacionService {

	List<Puntuacion> buscarPuntuaciones(String id, String jwt, String user);

	Puntuacion buscarPuntuacion(String id, String subId, String jwt, String user);

	Boolean editarPuntuacion(Long reglamento, Puntuacion puntuacion, String jwt, String user);

	Boolean crearPuntuacion(Long reglamento, Puntuacion puntuacion, String jwt, String user);

	Boolean borrarPuntuacion(String id, String jwt, String user);

	void sendFile(File serverFile, Long idSesion, String jwt, String user);

}
