package com.karsmaras.backend.services;

import java.util.List;

import com.karsmaras.backend.dto.CampeonatoDTO;
import com.karsmaras.backend.entity.Campeonato;

public interface CampeonatoService {

	Campeonato crearCampeonato(CampeonatoDTO campeonatoDto);

	List<Campeonato> getAllCampeonatos();

	List<Campeonato> getCampeonato(Integer id);

	Campeonato updateCampeonato(Integer id, CampeonatoDTO reglamentoDto);

	boolean borrarCampeonato(Integer id);
		
}
