package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Reglamento;

/**
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface ReglamentoService {

	List<Reglamento> buscarReglamentos();

	Reglamento buscarReglamento(String id);

	Boolean borrarReglamento(String id);

	Boolean crearReglamento(Reglamento reglamento);

	Boolean editarReglamento(Reglamento reglamento);
	
}
