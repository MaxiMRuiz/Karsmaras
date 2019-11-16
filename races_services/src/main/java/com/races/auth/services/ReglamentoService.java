package com.races.auth.services;

import java.util.List;

import com.races.auth.entity.Reglamento;

public interface ReglamentoService {

	Reglamento crearReglamento(Reglamento reglamentoDto);

	List<Reglamento> getAllReglamentos();

	List<Reglamento> getReglamento(Integer id);

	Reglamento updateReglamento(Integer id, Reglamento reglamentoDto);

	boolean borrarReglamento(Integer id);
	
}
