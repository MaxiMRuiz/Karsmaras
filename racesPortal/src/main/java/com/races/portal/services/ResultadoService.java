package com.races.portal.services;

import java.io.File;
import java.util.List;

import com.races.portal.dto.Resultado;

public interface ResultadoService {

	List<Resultado> buscarResultados(Long idSesion, String jwt, String user);

	Resultado buscarResultado(Long id, String jwt, String user);

	void editarResultado(Resultado resultado, String jwt, String user);

	void sendFile(File serverFile, Long idSesion, Long idGp, String jwt, String user);

}
