package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Sancion;

public interface SancionService {

	List<Sancion> buscarSanciones(Long idResultado, String jwt, String user);

	Boolean borrarSancion(Long id, String jwt, String user);

	Sancion buscarSancion(String id, String jwt, String user);

	Boolean editarSancion(Sancion sancion, String jwt, String user);

	Boolean crearSancion(Sancion sancion, String jwt, String user);

}
