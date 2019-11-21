package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;


public interface CampeonatoService {

	Campeonato crearCampeonato(CampeonatoDto campeonatoDto) throws RacesException;

	List<Campeonato> getAllCampeonatos(Long id, String nombre, String temporada);

	Campeonato getCampeonato(Long id) throws RacesException;

	Campeonato updateCampeonato(Long id, CampeonatoDto campeonatoDto) throws RacesException;

	boolean borrarCampeonato(Long id) throws RacesException;

	boolean existsCampeonato(Long idCampeonato);
		
}
