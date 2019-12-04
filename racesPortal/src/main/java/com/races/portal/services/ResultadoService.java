package com.races.portal.services;

import java.io.File;
import java.util.List;

import com.races.portal.dto.Resultado;

public interface ResultadoService {

	List<Resultado> buscarResultados(Long idSesion);

	Resultado buscarResultado(Long id);

	void editarResultado(Resultado resultado);

	void sendFile(File serverFile, Long idSesion, Long idGp);

}
