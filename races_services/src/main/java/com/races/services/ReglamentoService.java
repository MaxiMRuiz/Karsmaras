package com.races.services;

import java.util.List;

import com.races.dto.ReglamentoDto;
import com.races.entity.Reglamento;

public interface ReglamentoService {

	Reglamento crearReglamento(Reglamento reglamentoDto);

	List<Reglamento> getAllReglamentos();

	Reglamento getReglamento(Long id);

	Reglamento updateReglamento(Long id, ReglamentoDto reglamentoDto);

	boolean borrarReglamento(Long id);
	
	boolean existsReglamento(Long id);
	
}
