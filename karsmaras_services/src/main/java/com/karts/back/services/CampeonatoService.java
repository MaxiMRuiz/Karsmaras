package com.karts.back.services;

import java.util.List;

import com.karts.back.dto.CampeonatoDTO;
import com.karts.back.entity.Campeonato;

public interface CampeonatoService {

	Campeonato crearCampeonato(CampeonatoDTO campeonatoDto);

	List<Campeonato> getAllCampeonatos();

	List<Campeonato> getCampeonato(Integer id);

	Campeonato updateCampeonato(Integer id, CampeonatoDTO reglamentoDto);

	boolean borrarCampeonato(Integer id);
		
}
