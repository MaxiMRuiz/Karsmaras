package com.karts.back.services;

import java.util.List;

import com.karts.back.dto.ReglamentoDTO;
import com.karts.back.entity.Reglamento;

public interface ReglamentoService {

	Reglamento crearReglamento(ReglamentoDTO reglamentoDto);

	List<Reglamento> getAllReglamentos();

	List<Reglamento> getReglamento(Integer id);

	Reglamento updateReglamento(Integer id, ReglamentoDTO reglamentoDto);

	boolean borrarReglamento(Integer id);
	
}
