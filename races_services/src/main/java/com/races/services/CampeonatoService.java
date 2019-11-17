package com.races.services;

import java.util.List;

import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;


public interface CampeonatoService {

	Campeonato crearCampeonato(CampeonatoDto campeonatoDto);

	List<Campeonato> getAllCampeonatos(Long id, String nombre, String temporada);

	Campeonato getCampeonato(Long id);

	Campeonato updateCampeonato(Long id, CampeonatoDto reglamentoDto);

	boolean borrarCampeonato(Long id);

	boolean existsCampeonato(Long idCampeonato);
		
}
