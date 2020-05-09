package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Reglamento;

/**
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface ReglamentoService {

	List<Reglamento> buscarReglamentos(String jwt, String user);

	Reglamento buscarReglamento(String id, String jwt, String user);

	Boolean borrarReglamento(String id, String jwt, String user);

	Boolean crearReglamento(Reglamento reglamento, String jwt, String user);

	Boolean editarReglamento(Reglamento reglamento, String jwt, String user);
	
}
