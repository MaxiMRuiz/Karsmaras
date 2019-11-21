package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.EquipoDto;
import com.races.entity.Equipo;

public interface EquipoService {

	Equipo updateEquipo(Long id, EquipoDto equipoDto) throws RacesException;

	Equipo getEquipo(Long id) throws RacesException;

	List<Equipo> getAllEquipos(Long id, String nombre, String alias);

	Equipo crearEquipo(EquipoDto equipoDto) throws RacesException;

	boolean borrarEquipo(Long id) throws RacesException;

	boolean existsEquipo(Long id);
}
