package com.races.services;

import java.util.List;

import com.races.dto.ResultadoDto;
import com.races.entity.Resultado;

public interface ResultadoService {

	Resultado crearResultado(ResultadoDto resultadoDto);

	List<Resultado> getAllResultado();

	Resultado getResultado(Long id);

	boolean existsResultado(Long id);
	
	boolean removeResultado(Long id);
	
}
