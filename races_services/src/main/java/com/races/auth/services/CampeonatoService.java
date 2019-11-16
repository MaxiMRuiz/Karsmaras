package com.races.auth.services;

import java.util.List;

import com.races.auth.entity.Campeonato;

public interface CampeonatoService {

	Campeonato crearCampeonato(Campeonato campeonatoDto);

	List<Campeonato> getAllCampeonatos();

	List<Campeonato> getCampeonato(Integer id);

	Campeonato updateCampeonato(Integer id, Campeonato reglamentoDto);

	boolean borrarCampeonato(Integer id);
		
}
