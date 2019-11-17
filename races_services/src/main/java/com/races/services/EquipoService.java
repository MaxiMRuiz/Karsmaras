package com.races.services;

import java.util.List;

import com.races.dto.EquipoDto;
import com.races.entity.Equipo;

public interface EquipoService {

	Equipo updateEquipo(Long id, EquipoDto equipoDto);

	Equipo getEquipo(Long id);

	List<Equipo> getAllEquipos(Long id, String nombre, String alias);

	Equipo crearEquipo(EquipoDto equipoDto);

	boolean borrarEquipo(Long id);
	
	boolean existsEquipo(Long id);	
}
