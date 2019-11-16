package com.races.auth.services;

import java.util.List;

import com.races.auth.entity.Equipo;

public interface EquipoService {

	Equipo crearEquipo(Equipo equipoDto);

	List<Equipo> getAllEquipos();

	List<Equipo> getEquipo(String alias);

	Equipo updateEquipo(String alias, Equipo equipoDto);

	boolean borrarEquipo(String alias);
	
}
