package com.karsmaras.backend.services;

import java.util.List;

import com.karsmaras.backend.dto.EquipoDTO;
import com.karsmaras.backend.entity.Equipo;

public interface EquipoService {

	Equipo crearEquipo(EquipoDTO equipoDto);

	List<Equipo> getAllEquipos();

	List<Equipo> getEquipo(String alias);

	Equipo updateEquipo(String alias, EquipoDTO equipoDto);

	boolean borrarEquipo(String alias);
	
}
