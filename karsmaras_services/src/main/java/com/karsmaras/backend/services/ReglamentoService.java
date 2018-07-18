package com.karsmaras.backend.services;

import java.util.List;

import com.karsmaras.backend.dto.ReglamentoDTO;
import com.karsmaras.backend.entity.Reglamento;

public interface ReglamentoService {

	Reglamento crearReglamento(ReglamentoDTO reglamentoDto);

	List<Reglamento> getAllReglamentos();

	List<Reglamento> getReglamento(Integer id);

	Reglamento updateReglamento(Integer id, ReglamentoDTO reglamentoDto);

	boolean borrarReglamento(Integer id);
	
}
