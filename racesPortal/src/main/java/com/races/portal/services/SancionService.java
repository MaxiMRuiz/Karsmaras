package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Sancion;

public interface SancionService {

	List<Sancion> buscarSanciones(Long idResultado);

	Boolean borrarSancion(Long id);

	Sancion buscarSancion(String id);

	Boolean editarSancion(Sancion sancion);

	Boolean crearSancion(Sancion sancion);

}
