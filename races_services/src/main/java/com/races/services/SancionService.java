package com.races.services;

import java.util.List;

import com.races.dto.SancionDto;
import com.races.entity.Sancion;

public interface SancionService {

	Sancion crearSancion(SancionDto sancionDto);

	List<Sancion> getAllSancion();

	Sancion getSancion(Long id);

	boolean existsSancion(Long id);
	
	boolean removeSancion(Long id);
	
}
