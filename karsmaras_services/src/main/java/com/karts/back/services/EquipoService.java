package com.karts.back.services;

import java.util.List;

import com.karts.back.dto.EquipoDTO;
import com.karts.back.entity.Equipo;

public interface EquipoService {

	Equipo crearEquipo(EquipoDTO equipoDto);

	List<Equipo> getAllEquipos();

	List<Equipo> getEquipo(String alias);

	Equipo updateEquipo(String alias, EquipoDTO equipoDto);

	boolean borrarEquipo(String alias);
	
}
