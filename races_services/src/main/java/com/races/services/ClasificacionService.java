package com.races.services;

import java.util.List;

import com.races.dto.ClasificacionDto;

public interface ClasificacionService {

	/**
	 * Calcula la clasificacion de un GP
	 * 
	 * @param idGp
	 * @return
	 */
	List<ClasificacionDto> calcularClasificacionGp(Long idGp);

	/**
	 * Calcula la clasificacion de un campeonato
	 * 
	 * @param id
	 * @return
	 */
	List<ClasificacionDto> calcularClasificacionCampeonato(Long id);

}
