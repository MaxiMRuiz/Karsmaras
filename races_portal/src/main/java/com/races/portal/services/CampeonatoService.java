package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Campeonato;

/**
 * Interfaz de servicios para Campeonatos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface CampeonatoService {

	/**
	 * Servicio de busqueda de campeonatos
	 * 
	 * @param id
	 * 
	 * @return
	 */
	List<Campeonato> buscarCampeonatos(Long id, String nombre, String descripcion, String temporada, String jwt, String user);

	/**
	 * Servicio de busqueda de un Campeonato
	 * 
	 * @param id
	 * @return
	 */
	Campeonato buscarCampeonato(String id, String jwt, String user);

	/**
	 * Servicio para editar un Campeonato
	 * 
	 * @param campeonato
	 * @return
	 */
	Boolean editarCampeonato(Campeonato campeonato, String jwt, String user);

	/**
	 * Servicio para crear un campeonato
	 * 
	 * @param campeonato
	 * @return
	 */
	Boolean crearCampeonato(Campeonato campeonato, String jwt, String user);

	/**
	 * Servicio para borrar un campeonato
	 * 
	 * @param id
	 * @return
	 */
	Boolean borrarCampeonato(String id, String jwt, String user);
}
